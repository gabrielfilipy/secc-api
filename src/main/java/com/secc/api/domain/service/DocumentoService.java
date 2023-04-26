package com.secc.api.domain.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
 
import com.secc.api.domain.exception.NotFoundDocumentoException;
import com.secc.api.domain.exception.RegraDeNegocioException;
import com.secc.api.domain.model.Documento;
import com.secc.api.domain.model.Movimentacao;
import com.secc.api.domain.repository.DocumentoRepository;
import com.secc.api.domain.repository.MovimentacaoRepository;
import com.secc.api.domain.repository.siga.PessoaRepository;
import com.secc.api.domain.service.siga.DocumentoSigaService;
import com.secc.api.domain.service.siga.SigaAssinadorService;
import com.secc.api.integracao.siga.config.Serializador;
import com.secc.api.integracao.siga.config.interceptor.Interceptador;
import com.secc.api.integracao.siga.model.ExDocumento;
import com.secc.api.util.Gerar;

@Service
public class DocumentoService  extends Interceptador {
	
	@Autowired
	private DocumentoSigaService documentoSigaService;
	
	@Autowired
	private SigaAssinadorService sigaAssinadorService;
	
	@Autowired
	private DocumentoRepository documentoRepository;
	
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private Gerar gerar;
	
	@Transactional
	public Documento criarDocumento(Documento documento) throws Exception {
		
		if(documento.getId() != null)
			verificaExistenciaDocumento(documento.getId());
		
		pessoaRepository.buscar(documento.getResponsavelAssinatura());
		pessoaRepository.buscar(documento.getCadastrante());
		
		if (!StringUtils.hasLength(documento.getSigla())) {
			gerar.gerarSigla(documentoRepository.count());
			documento.setSigla(gerar.sigla());
		}
		
		return documentoRepository.save(documento);
	}
	
	@Transactional
	public Documento atualizarEstadoDoDocumento(String sigla, String assinado) {
		Documento documento = verificaExistenciaDocumento(sigla);
		documento.setSigla(assinado);
		documento.setDataAssinatura(OffsetDateTime.now());
		
		return documentoRepository.save(documento);
	}
	
	@Transactional
	public Documento atualizarDataExclusaoDocumento(String sigla) {
		Documento documento = verificaExistenciaDocumento(sigla);
		documento.setDataExclusao(OffsetDateTime.now());
		
		return documentoRepository.save(documento);
	}
	
	@Transactional
	public Object finalizarDocumento(String siglaMobil) {
		try {
			String token = getAuthorization();
			Documento documento = verificaExistenciaDocumento(siglaMobil);
			String documentoTemporario = criarDocumentoTemporarioNoSiga(documento);
			ExDocumento exDocumento = documentoSigaService.finalizarDocumento(documentoTemporario, token);
			
			incluirCossignatarios(documento.getSigla(), documentoTemporario, token);
			
			return atualizarEstadoDoDocumento(documento.getSigla(), exDocumento.getSigla()); 
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	public Documento assinarDocumento(Documento documento, String tokenAssinante) throws Exception {
		try {
			String token = getAuthorization();
			String siglaDocumentoSecc = documento.getSigla();
			
			String assinador = Serializador.decodeTokenJwtAssinador(tokenAssinante);
			
			if(!assinador.equals(documento.getResponsavelAssinatura()))
				  throw new RegraDeNegocioException("Só é permitida a assinatura do subscritor do documento.");
			
			String documentoTemporario = criarDocumentoTemporarioNoSiga(documento);
			incluirCossignatarios(siglaDocumentoSecc, documentoTemporario, token);
			String siglaDocumentoAssinado = assinarDocumentoTemporario(documentoTemporario, tokenAssinante);
			
			return atualizarEstadoDoDocumento(siglaDocumentoSecc, siglaDocumentoAssinado); 
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	private void validaAssinanteDoDocumento(Documento documento, String siglaDocumentoSecc, String assinador) {
		List<Movimentacao> movimentacoes = movimentacaoRepository.buscarMovimentacoesDoDocumento(siglaDocumentoSecc);
		
		if(!assinador.equals(documento.getResponsavelAssinatura()))
			  throw new RegraDeNegocioException("Só é permitida a assinatura do subscritor e dos cossignatários do documento.");
		
//		if(!assinador.equals(documento.getResponsavelAssinatura()))
//			throw new RegraDeNegocioException("Só é permitida a assinatura do subscritor e dos cossignatários do documento.");
	}
	
	@Transactional
	public Documento prepararDocumentoParaAssCertificado(Documento documento, String token) throws Exception {
		try {
			String siglaDocumentoSecc = documento.getSigla();
			documento.setSigla(null);
			String documentoTemporario = criarDocumentoTemporarioNoSiga(documento);
			incluirCossignatarios(siglaDocumentoSecc, documentoTemporario, token);
			
			return atualizarEstadoDoDocumento(siglaDocumentoSecc, documentoTemporario); 
		} catch (Exception e) {
			throw new RegraDeNegocioException(e.getMessage());
		}
	}

	private void incluirCossignatarios(String siglaDocumentoSecc, String documentoTemporario, String token) {
		List<Movimentacao> movimentacoes = movimentacaoRepository.buscarMovimentacoesDoDocumento(siglaDocumentoSecc);
		
		if(movimentacoes.size() > 0) {
			for (Movimentacao mov : movimentacoes) { 
				if(mov.getDataFinalizacao() == null)
					documentoSigaService.incluirCossignatario(documentoTemporario, mov.getSiglaSubscritor(),
							mov.getCargo(), mov.getLotacao(), null, token);
			}
		}
	}

	private String assinarDocumentoTemporario(String tmp, String token) {
		String documentoAssinado = sigaAssinadorService.assinarDocumentoComSenha(tmp, token).toString()
				.replace("{sigla=", "")
				.replace(", status=OK}", "");
		return documentoAssinado;
	}
 
	private String criarDocumentoTemporarioNoSiga(Documento documento) {
		ExDocumento exDocumento = new ExDocumento();
		exDocumento.setEntrevista("Interessado=" + documento.getInteressado() 
			+ "&Assunto=" + documento.getAssunto()
			+ "&Referência=" + documento.getNumReferencia() 
			+ "&mem=" + documento.getTextoMemorando());
		
		exDocumento.setModelo(documento.getModelo());
		exDocumento.setSubscritor(documento.getResponsavelAssinatura());
		exDocumento.setTitular(documento.getCadastrante()); 
		
		Object obj = documentoSigaService.criarUmNovoDocumento(exDocumento).toString()
						.replace("}", "")
						.replace("{sigladoc", "")
						.replace("=", "");
		
		return obj.toString();
	}
	
	public Documento verificaExistenciaDocumento(String sigla) {
		if(documentoRepository.findBySigla(sigla) == null) 
			throw new NotFoundDocumentoException(sigla);
		
		return documentoRepository.findBySigla(sigla);
	}
	
	public Optional<Documento> verificaExistenciaDocumento(Long id) {
		if(documentoRepository.findById(id).isEmpty()) 
			throw new NotFoundDocumentoException(id, "");
		
		return documentoRepository.findById(id);
	}
	
}
