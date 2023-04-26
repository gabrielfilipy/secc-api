package com.secc.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.secc.api.domain.service.siga.SigaAssinadorService;
import com.secc.api.integracao.siga.model.Certificado;

import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition(info = @Info(title = "SECC-API", description = "Assinador", version = "1.0"))
@RestController
@RequestMapping("/assinador")
public class AssinadorController {

	@Autowired
	private SigaAssinadorService service;
	
	@PostMapping("/{sigla}/hash")
	public Object assinarHash(@PathVariable String sigla) {
		return service.criarHash(sigla);
	}
	
	@PostMapping("/{sigla}/sign")
	public Object assinarSave(@PathVariable String sigla, @RequestBody Certificado certificado) {
		return service.salvarAssinatura(certificado, sigla);
	}
	
}

