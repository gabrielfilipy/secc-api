package com.secc.api.integracao.siga.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentoEditado {
	
	@JsonProperty("sigla")
	private String sigla;
	
	@JsonProperty("entrevista")
	private String entrevista;
	
	@JsonProperty("conteudoBlobHtmlString")
	private String conteudoBlobHtmlString;
	
	@JsonProperty("interessado")
	private String interessado;
	
	@JsonProperty("assunto")
	private String assunto;
	
	@JsonProperty("referencia")
	private String referencia;
	
	@JsonProperty("mem")
	private String mem;
	
	@JsonProperty("modelo")
	private String modelo;
	
	@JsonProperty("subscritorSigla")
	private String subscritorSigla;
	
	@JsonProperty("subscritorNome")
	private String subscritorNome;
	
	@JsonProperty("subscritor")
	List<Subscritor> subscritor = new ArrayList<>();
	
}
