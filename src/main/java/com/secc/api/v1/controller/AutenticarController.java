package com.secc.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.secc.api.domain.service.siga.AutenticarService;
import com.secc.api.integracao.siga.config.interceptor.Interceptador;

import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition(info = @Info(title = "SECC-API", description = "Aunteticador", version = "1.0"))
@RestController
@RequestMapping("/autenticar")
public class AutenticarController extends Interceptador{

	 @Autowired
	 private AutenticarService service;
	 
	 @PostMapping()
	 @ResponseStatus(HttpStatus.OK)
	 public Object autenticar() throws Exception {
		 return service.autenticarUsuario();
	 }
	
}

