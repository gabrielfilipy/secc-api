package com.secc.api.integracao.siga.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonRootName("orgao")
@JsonPropertyOrder({
	"nome", 
	"sigla"
})
public class DpOrgao {

	private String nome;
	private String sigla;
	
}
