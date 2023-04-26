package com.secc.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextoPadraoDTO {

private Long id;
	
	@NotBlank
	@NotNull
	private String siglaPessoa;
	
	@NotBlank
	@NotNull
	private String modelo;
	
	@NotBlank
	@NotNull
	private String titulo;
	
	private String interessado;
	
	private String assunto;
	
	private String numReferencia;
	
	private String textoMemorando;
	
}
