package com.secc.api.integracao.siga.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonRootName("lotacao")
@JsonPropertyOrder({
	"nome", 
	"orgao"
})
public class DpLotacao {

	private String nome;
	private String siglaLotacao;
	private String sigla;
	private DpOrgao orgao;
	
}
