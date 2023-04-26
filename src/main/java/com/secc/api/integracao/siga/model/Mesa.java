package com.secc.api.integracao.siga.model;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonRootName("mesa")
public class Mesa {

	private String sigla;
	private String documentoData;
	private String documentoSubscritor;
	private String documentoLotaSubscritor;
	private String documentoEspecie;
	private String documentoModelo;
	private String documentoDescricao;
	private String marcadorId;
	private String marcadorNome;
	private String marcaData;
	private String marcaResponsavel;
	
}
