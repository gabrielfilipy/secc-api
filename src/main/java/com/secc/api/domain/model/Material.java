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
@Table(name = "material_item")
public class Material {

	@Id
	@Column(name = "cd_material")
    private Long cdMaterial;

	@Column(name = "cd_item")
    private Long cdItem;
    
	@Column(name = "tx_descricao_item")
    private String txDescricaoItem;
    
    @Column(name = "cd_natureza_despesa_1")
    private Long cdNaturezaDespesa1;
    
    @Column(name = "cd_natureza_despesa_2")
    private Long cdNaturezaDespesa2;
    
    @Column(name = "cd_natureza_despesa_3")
    private Long cdNaturezaDespesa3;
    
    @Column(name = "cd_bec_status")
    private Long cdBecStatus;
    
    @Column(name = "cd_status_item")
    private Long cdStatusItem;
    
    @Column(name = "data_alteracao")
    private String dataAlteracao;
    
    @Column(name = "data_movimento")
    private String dataMovimento;
    
    @Column(name = "cd_selo_verde")
    private Long cdSeloVerde;
    
    @Column(name = "data_max_negociacao")
    private String dataMaxNegociacao;
    
	@ManyToOne
	@JoinColumn(name="cd_material", nullable=true, insertable=false, updatable=false)
	private MaterialMaterial materialMaterial;
	
	@ManyToOne
	@JoinColumn(name="cd_item", nullable=true, insertable=false, updatable=false)
	private MaterialItemCaracteristica materialItemCaracteristica;
	
}
