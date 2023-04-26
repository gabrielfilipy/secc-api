package com.secc.api.integracao.siga.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SigaModel {

	@JsonProperty(namespace = "token")
	private String token;
	
}
