package com.secc.api.integracao.siga.model;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExDocumento {

	@JsonProperty("sigladoc")
	private String sigladoc;
	
	@JsonProperty("sigla")
	private String sigla;
	
	@JsonProperty("modelo")
	private String modelo;
	
	@JsonProperty("siglamobilpai")
	private String siglamobilpai;
	
	@JsonProperty("siglamobilfilho")
	private String siglamobilfilho;
	
	@JsonProperty("subscritor")
	private String subscritor;
	
	@JsonProperty("titular")
	private String titular;
	
	@JsonProperty("eletronico")
	private Boolean eletronico;
	
	@JsonProperty("descricaotipodoc")
	private String descricaotipodoc;
	
	@JsonProperty("classificacao")
	private String classificacao;
	
	@JsonProperty("pessoadestinatario")
	private String pessoadestinatario;
	
	@JsonProperty("lotadestinatario")
	private String lotadestinatario;
	
	@JsonProperty("orgaoexternodestinatario")
	private String orgaoexternodestinatario;
	
	@JsonProperty("destinatariocampoextra")
	private String destinatariocampoextra;
	
	@JsonProperty("descricaodocumento")
	private String descricaodocumento;
	
	@JsonProperty("nivelacesso")
	private String nivelacesso;
	
	@JsonProperty("entrevista")
	private String entrevista;
	
	@JsonProperty("arquivo")
	private File arquivo;
	
	@JsonProperty("arquivoGerado")
	private String arquivoGerado;
	
	@JsonProperty("status")
	private String status;
	
}
