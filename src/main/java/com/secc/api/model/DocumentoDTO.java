package com.secc.api.model;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentoDTO {

	private Long id;
	private String sigla;
	private Long idCatalogo;
	private String responsavelAssinatura;
	private boolean substituto;
	private String interessado;
	private String cadastrante;
	private String assunto;
	private String numReferencia;
	private String textoMemorando;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSSx")
	private OffsetDateTime dataCriacao;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSSx")
	private OffsetDateTime dataAssinatura;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSSx")
	private OffsetDateTime dataExclusao;
	private String modelo; 
	
}
