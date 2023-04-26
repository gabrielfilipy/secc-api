package com.secc.api.v1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import com.secc.api.domain.exception.RegraDeNegocioException;
import com.secc.api.domain.model.Documento;
import com.secc.api.domain.repository.DocumentoRepository;
import com.secc.api.domain.service.DocumentoService;
import com.secc.api.integracao.siga.config.interceptor.Interceptador;
import com.secc.api.model.DocumentoDTO;
import com.secc.api.model.input.DocumentoDTOInput;
import com.secc.api.v1.assembler.DocumentoDTOAssembler;
import com.secc.api.v1.assembler.DocumentoInputDisassembler;

import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition(info = @Info(title = "SECC-API", description = "Gestão Documental.", version = "1.0"))
@RestController
@RequestMapping("/documento")
public class DocumentoController extends Interceptador { 
	
	@Autowired
	private DocumentoRepository documentoRepository;
	
	@Autowired
	private DocumentoService service;
	
	@Autowired
	private DocumentoDTOAssembler assembler;
	
	@Autowired
	private DocumentoInputDisassembler disassembler;
	
	//Serve para criar ou editar um documento.
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Cria um documento.")
	public DocumentoDTO criarDocumento(@RequestBody @Valid DocumentoDTOInput documentoDTOInput) throws Exception {
		try {
			Documento documento = disassembler.toDomainObject(documentoDTOInput);

			return assembler.toModel(service.criarDocumento(documento));
		} catch (Exception e) {
			throw new RegraDeNegocioException(e.getMessage());
		}
	}
	
	@GetMapping()
	@ApiOperation("Carregar documentos.")
	public Page<DocumentoDTO> carregarDocumentosCriados(@RequestParam("page") int page, 
			@RequestParam("size") int size, String siglaCadastrante, 
			Boolean dataAssinatura, Boolean dataExclusao) throws Exception{
		
		Page<Documento> documentoPage = documentoRepository.carregarDocumentos(siglaCadastrante, 
				dataAssinatura, dataExclusao, page, size);
		
		List<DocumentoDTO> documentoDTO = assembler
				.toCollectionModel(documentoPage.getContent());
		
		Page<DocumentoDTO> documentoDTOPage = new PageImpl<>(
				documentoDTO, PageRequest.of(page, size), documentoPage.getTotalElements());
		
		return documentoDTOPage; 
	}
	
	@PostMapping("/assinar-com-senha")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Assinar um documento com senha.")
	public DocumentoDTO assinarDocumento(@RequestBody DocumentoDTOInput documentoDTOInput, String tokenAssinante) throws Exception {
		try {
			Documento documento = disassembler.toDomainObject(documentoDTOInput);
			
			return assembler.toModel(service.assinarDocumento(documento, tokenAssinante));
		} catch (Exception e) {
			throw new RegraDeNegocioException(e.getMessage());
		}
	}
	
	@PostMapping("/prepara-documento-certificado")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Assinar um documento com certificado digital.")
	public DocumentoDTO preprarDocumentoParaAssCertificado(@RequestBody DocumentoDTOInput documentoDTOInput) throws Exception {
		try {
			Documento documento = disassembler.toDomainObject(documentoDTOInput);
			
			return assembler.toModel(service.prepararDocumentoParaAssCertificado(documento, getAuthorization()));
		} catch (Exception e) {
			throw new RegraDeNegocioException(e.getMessage());
		}
	}
	
	@GetMapping("/visualizar/{sigla}")
	@ApiOperation("Buscar um documento criado.")
	public DocumentoDTO visualizar(@PathVariable String sigla) {
		Documento documento = service.verificaExistenciaDocumento(sigla);
		
		return assembler.toModel(documento);
	}
	
	@GetMapping("/buscar/{id}")
	@ApiOperation("Buscar um documento criado.")
	public DocumentoDTO visualizar(@PathVariable Long id) {
		Optional<Documento> documento = service.verificaExistenciaDocumento(id);
		
		return assembler.toModel(documento);
	}
	
	@PostMapping("/excluir/{sigla}")
	@ApiOperation("Excluir um documento.")
	public DocumentoDTO excluirDocumento(@PathVariable String sigla) throws Exception {
		return assembler.toModel(service.atualizarDataExclusaoDocumento(sigla));
	}
	
	@PostMapping("/finalizar-documento/{sigla}")
	@ApiOperation("Finalizar um documento.")
	public Object finalizarDocumento(@PathVariable String sigla) {
		return service.finalizarDocumento(sigla);
	}
	
}