package com.secc.api.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "servico_servico")
public class ServicoServico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_servico")
	private Long cdServico;
	
	@Column(name = "cd_classe")
	private Long cdClasse;

	@Column(name = "tx_nome_servico")
	private String txNomeServico;

	@Column(name = "dt_alteracao")
	private String dtAlteracao;

	@Column(name = "cd_status_servico")
	private Long cdStatusServico;

	@Column(name = "cd_agrupamento")
	private String cdAgrupamento;
	
	
	  @ManyToOne
	  @JoinColumn(name = "cd_classe", insertable=false, updatable=false) private
	  ServicoClasse servicoClasse;
	 
}
