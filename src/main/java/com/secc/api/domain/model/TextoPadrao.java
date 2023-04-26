package com.secc.api.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TextoPadrao {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@NotNull
	@Column(name = "sigla_pessoa")
	private String siglaPessoa;
	
	@NotBlank
	@NotNull
	@Column(name = "modelo")
	private String modelo;
	
	@NotBlank
	@NotNull
	private String titulo;
	
	@Column(name = "interessado")
	private String interessado;
	
	@Column(name = "assunto")
	private String assunto;
	
	@Column(name = "num_referencia")
	private String numReferencia;
	
	@Column(name = "texto_memorando")
	private String textoMemorando;
	
}
