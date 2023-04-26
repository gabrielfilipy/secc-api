package com.secc.api.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "servico_grupo")
public class ServicoGrupo {

	@Id
	@Column(name = "cd_grupo")
	private Integer cdGrupo;


	@Column(name = "tx_nome_grupo")
	private String txNomeGrupo;


	@Column(name = "dt_alteracao")
	private String dtAlteracao;

	
	@Column(name = "tx_nota_grupo")
	private String txNotaGrupo;

	@Column(name = "cd_status_grupo")
	private Integer cdStatusGrupo;

}
