package com.secc.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secc.api.domain.model.Servico;
import com.secc.api.domain.repository.ServicoRepository;
import com.secc.api.integracao.siga.config.interceptor.Interceptador;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition(info = @Info(title = "SECC-API", description = "Gestão Documental.", version = "1.0"))
@RestController
@RequestMapping("/servico")
public class PesquisaServicoController extends Interceptador {

	@Autowired
	private ServicoRepository servicoRepository;

	@GetMapping("/servico-descricao")
	@ApiOperation("Busca os serviços por descrição.")
	public List<Servico> pesquisarPorDesc(String desc) {
		return servicoRepository.pesquisarPorDesc(desc);
	}

	@GetMapping("/servico-codigo")
	@ApiOperation("Busca o serviço por codigo")
	public List<Servico> pesquisarPorCodigo(String codigo) {
		return servicoRepository.pesquisarPorCodigo(codigo);
	}

}