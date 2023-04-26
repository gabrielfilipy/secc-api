package com.secc.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secc.api.domain.model.Material;
import com.secc.api.domain.repository.MaterialRepository;
import com.secc.api.integracao.siga.config.interceptor.Interceptador;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition(info = @Info(title = "SECC-API", description = "Gestão Documental.", version = "1.0"))
@RestController
@RequestMapping("/material")
public class PesquisaMaterialController extends Interceptador {

	@Autowired
	private MaterialRepository materialRepository;

	@GetMapping("/material-descricao")
	@ApiOperation("Busca os materiais por descrição.")
	public List<Material> pesquisarPorDesc(String desc) {
		System.out.println(desc);
		return materialRepository.pesquisarPorDesc(desc);
	}

	@GetMapping("/material-codigo")
	@ApiOperation("Busca o material por codigo")
	public List<Material> pesquisarPorCodigo(Integer codigo) {
		return materialRepository.pesquisarPorCodigo(codigo);
	}
	
	@GetMapping("/material-codigo-find")
	@ApiOperation("Busca o material por codigo")
	public List<Material> findByCdMaterial(Integer codigo) {
		return materialRepository.pesquisarPorCodigo(codigo);
	}

}