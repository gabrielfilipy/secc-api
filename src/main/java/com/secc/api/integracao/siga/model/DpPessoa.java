package com.secc.api.integracao.siga.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonRootName("pessoa")
@JsonPropertyOrder({
	"idPessoa", 
	"idPessoaIni",
	"sigla", 
	"nome", 
	"email", 
	"isExternaPessoa"
})
public class DpPessoa { 

	@JsonProperty("idPessoa")
	private String idPessoa;
	
	@JsonProperty("idPessoaIni")
	private String idPessoaIni;
	
	@JsonProperty("sigla")
	private String sigla;
	
	@JsonProperty("senha")
	private String senha;
	
	@JsonProperty("nome")
	private String nome;
	
	@JsonProperty("email")
	private String email;
	
	private Boolean isExternaPessoa;
	
	private String siglaLotacao;
	
	private DpCargo cargo;
	
	private DpLotacao lotacao;
	
	
//	private DpLotacao lotacao;
//	private DpCargo cargo;
	
}
