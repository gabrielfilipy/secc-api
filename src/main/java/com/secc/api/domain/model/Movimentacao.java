package com.secc.api.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.secc.api.Groups;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Movimentacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@NotNull
	@Column(name = "sigla_subscritor", nullable = false)
	private String siglaSubscritor;
	
	@NotBlank
	@NotNull
	@Column(name = "lotacao", nullable = false)
	private String lotacao;
	
	@NotBlank
	@NotNull
	@Column(name = "cargo", nullable = false)
	private String cargo;
	
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	private OffsetDateTime dataCriacao;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	private OffsetDateTime dataFinalizacao;
	
	@NotNull
	@DecimalMin("1")
	@Column(name = "tipo_movimentacao", nullable = false)
	private Integer tipoMovimentacao;
	
	@Valid
	@ConvertGroup(from = Default.class, to = Groups.documentoId.class)
	@NotNull
	@ManyToOne 
	private Documento documento;
	
}