package com.secc.api.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
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
import com.secc.api.domain.model.Documento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimentacaoDTO {

	private Long id;
	private String siglaSubscritor;
	
	@NotBlank
	@NotNull
	private String lotacao;
	
	@NotBlank
	@NotNull
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
	
	private DocumentoDTO documento;
	
}
