package com.secc.api.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.secc.api.Groups;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name= "documento", schema = "Dbmesaexternasp")
@Entity
public class Documento {

	@NotNull(groups = Groups.documentoId.class)
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "sigla", nullable = false)
	private String sigla;

	@Column(name = "id_catalogo")
	private Long idCatalogo;

	@Column(name = "tipo_aquisicao")
	private String tipoAquisicao;

	@Column(name = "meus_textos_padroes")
	private String meusTextosPadrao;

	@NotBlank()
	@NotNull
	@Column(name = "resp_assinatura")
	private String responsavelAssinatura;

	@Column(name = "substituto")
	private boolean substituto;

	@Column(name = "personalizar")
	private boolean personalizar;

	@Column(name = "interessado")
	private String interessado;
	
	@NotBlank
	@NotNull
	@Column(name = "cadastrante", nullable = false)
	private String cadastrante;

	@NotBlank
	@NotNull
	@Column(name = "assunto")
	private String assunto;

	@NotBlank
	@NotNull
	@Column(name = "num_referencia")
	private String numReferencia;

	@NotBlank
	@NotNull
	@Column(name = "texto_memorando")
	private String textoMemorando;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSSx")
	@CreationTimestamp
	@Column(name = "data_criacao", columnDefinition = "datetime") 
	private OffsetDateTime dataCriacao;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSSx")
	@Column(name = "data_assinatura", columnDefinition = "datetime")
	private OffsetDateTime dataAssinatura;
	
	@Column(name = "data_exclusao", columnDefinition = "datetime")
	private OffsetDateTime dataExclusao;
	
	@NotBlank 
	@NotNull
	@Column(name = "modelo", nullable = false)
	private String modelo; 

}
