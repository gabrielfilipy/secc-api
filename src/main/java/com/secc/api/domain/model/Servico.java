package com.secc.api.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "servico_item" )
public class Servico {

	@Id
	@Column(name = "cd_servico")
	private Long cdServico;

	@Column(name = "cd_Item")
	private Long cdItem;

	@Column(name = "tx_descricao_item")
	private String txDescricaoItem;

	@Column(name = "cd_status_item")
	private Long cdStatusItem;

	@Column(name = "cd_bec_status")
	private Long cdBecStatus;

	@Column(name = "dt_st_item")
	private String dtStItem;

	@Column(name = "dt_alteracao")
	private String dataAlteracao;

	@Column(name = "data_max_negociacao")
	private String dataMaxNegociacao;
	
	@ManyToOne
	@JoinColumn(name="cd_servico", nullable=true, insertable=false, updatable=false)
	private ServicoServico servicoServico;
	
}
