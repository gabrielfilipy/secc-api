package com.secc.api.v1.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secc.api.domain.repository.siga.PessoaRepository;
import com.secc.api.integracao.siga.model.DpPessoa;

import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition(info = @Info(title = "SECC-API", description = "Usuário", version = "1.0"))
@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository; 
	
	@GetMapping("/listar/{texto}")
	public List<DpPessoa> listar(@PathVariable String texto) throws IOException, InterruptedException {
		return pessoaRepository.listar(texto);  
	}
	
	@GetMapping("/buscar/{sigla}")
	public DpPessoa buscar(@PathVariable String sigla) throws Exception {
		return pessoaRepository.buscar(sigla);
	}
	
}