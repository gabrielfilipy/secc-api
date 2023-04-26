package com.secc.api.model.input;

import java.time.OffsetDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentoDTOInput {

	private Long id;
	private Long idCatalogo;
	private String sigla;
	
	@NotBlank()
	@NotNull
	private String responsavelAssinatura;
	private boolean substituto;
	private String interessado;
	
	@NotBlank
	@NotNull
	private String cadastrante;
	
	@NotBlank
	@NotNull
	private String assunto;
	
	@NotBlank
	@NotNull
	private String numReferencia;
	
	@NotBlank
	@NotNull
	private String textoMemorando;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSSx")
	private OffsetDateTime dataAssinatura;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSSx")
	private OffsetDateTime dataExclusao;
	
	@NotBlank
	@NotNull
	private String modelo; 
	
}
