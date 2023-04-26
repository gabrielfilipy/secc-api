package com.secc.api.domain.service;

import java.time.OffsetDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secc.api.domain.exception.NotFoundDocumentoException;
import com.secc.api.domain.exception.NotFoundMovimentacaoException;
import com.secc.api.domain.exception.RegraDeNegocioException;
import com.secc.api.domain.model.Documento;
import com.secc.api.domain.model.Movimentacao;
import com.secc.api.domain.repository.DocumentoRepository;
import com.secc.api.domain.repository.MovimentacaoRepository;
import com.secc.api.domain.repository.siga.PessoaRepository;
import com.secc.api.enums.TipoMovimentacao;

@Service
public class MovimentacaoService {

	public static final String MSG_ERRO = "could not execute statement";
	public static final Integer TIPO_MOVIMENTACAO = TipoMovimentacao.INCLUIR_COSSIGNATARIO.getCod();
	
	@Autowired
	private MovimentacaoRepository repository;
	
	@Autowired
	private DocumentoRepository documentoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Transactional
	public Movimentacao incluirCossignario(Movimentacao movimentacao) throws Exception {
		pessoaRepository.buscar(movimentacao.getSiglaSubscritor());
		Long documentoId = movimentacao.getDocumento().getId();
		Documento documento = documentoRepository.buscarDocumentoValidoPeloId(documentoId);
		
		if(documento == null) 
			throw new NotFoundDocumentoException(movimentacao.getDocumento().getId(), "");
		
		Movimentacao mov = repository.buscaMovimentacaoFinalizada(documento.getSigla(), 
				movimentacao.getSiglaSubscritor(), TIPO_MOVIMENTACAO);

		if(mov != null)
			throw new RegraDeNegocioException("Não é possível ter cossignatários duplicados no documento.");
		
				
		movimentacao.setTipoMovimentacao(TipoMovimentacao.INCLUIR_COSSIGNATARIO.getCod());
		movimentacao.setDocumento(documento);
			
		return repository.save(movimentacao);
		
	}
	
	@Transactional
	public void excluirMovimentacao(String siglaDocumento, Integer tipoMovimentacao, String siglaSubscrito) throws Exception {
		pessoaRepository.buscar(siglaSubscrito);
		Movimentacao movimentacao = repository.buscaMovimentacaoFinalizada(
				siglaDocumento, siglaSubscrito, tipoMovimentacao);
		
		if(movimentacao == null)
			throw new NotFoundMovimentacaoException("Movimentação não existe.");
		
		movimentacao.setDataFinalizacao(OffsetDateTime.now());
		repository.save(movimentacao);
	}
	
}