package com.secc.api.v1.controller;

import java.time.OffsetDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.secc.api.domain.model.Movimentacao;
import com.secc.api.domain.repository.MovimentacaoRepository;
import com.secc.api.domain.service.MovimentacaoService;
import com.secc.api.model.MovimentacaoDTO;
import com.secc.api.model.input.MovimentacaoDTOInput;
import com.secc.api.v1.assembler.MovimentacaoDTOAssembler;
import com.secc.api.v1.assembler.MovimentacaoInputDisassembler;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition(info = @Info(title = "SECC-API", description = "Movimentação de documentos", version = "1.0"))
@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoController {

	@Autowired
	private MovimentacaoService service;
	
	@Autowired
	private MovimentacaoRepository repository;
	
	@Autowired
	private MovimentacaoDTOAssembler assembler;
	
	@Autowired
	private MovimentacaoInputDisassembler disassembler;
	
	@PostMapping("/incluir-cossignatario")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Cria uma movimentacao.")
	public MovimentacaoDTO incluirCossignatario(@RequestBody @Valid 
			MovimentacaoDTOInput movimentacaoDTOInput) throws Exception {
		Movimentacao movimentacao = disassembler.toDomainObject(movimentacaoDTOInput);
		
		return assembler.toModel(service.incluirCossignario(movimentacao));
	}
	
	@PostMapping("/excluir-movimentacao")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Excluir uma movimentacao.")
	public void excluirMovimentacao(String siglaDocumento, Integer tipoMovimentacao, 
			String siglaSubscritor) throws Exception {
		service.excluirMovimentacao(siglaDocumento, tipoMovimentacao, siglaSubscritor);
	}
	
	@GetMapping("/relatorio")
	public List<MovimentacaoDTO> movimentacoes(String subscritor, Integer tipoMovimentacao,
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)OffsetDateTime  dataCriacao) {
		return assembler.toCollectionModel(
				repository.movimentacoes(subscritor, dataCriacao, tipoMovimentacao));
	}
	
	@GetMapping("/buscar-movimentacoes")
	public List<MovimentacaoDTO> movimentacoes(String siglaDocumento, Integer tipoMovimentacao) {
		return assembler.toCollectionModel(repository.movimentacoes(siglaDocumento, tipoMovimentacao));
	}
	
}
