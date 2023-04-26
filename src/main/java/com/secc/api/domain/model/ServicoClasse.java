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
@Table(name = "servico_classe")
public class ServicoClasse {

	
	@Column(name = "cd_grupo")
	private Long cdGrupo;

	@Id
	@Column(name = "cd_classe")
	private Long cdClasse;

	@Column(name = "tx_descricao_classes")
	private String txDescricaoClasses;

	@Column(name = "dt_alteracao")
	private String dtAlteracao;

	@Column(name = "tx_nota_classe")
	private String txNotaClasse;

	@Column(name = "cd_status_classe")
	private Long cdStatusClasse;
	
	
	  @ManyToOne
	  
	  @JoinColumn(name = "cd_grupo" , insertable=false, updatable=false) private
	  ServicoGrupo servicoGrupo;
	 

}
