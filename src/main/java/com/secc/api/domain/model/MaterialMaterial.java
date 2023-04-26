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
@Table(name = "material_material")
public class MaterialMaterial {
	@Id
	@Column(name = "cd_classe")
    private Long cdClasse;

	@Column(name = "cd_material")
    private Long cdMaterial;
    
	@Column(name = "tx_material")
    private String txMaterial;
    
    @Column(name = "data_alteracao")
    private String dataAlteracao;
    
    @Column(name = "data_movimento")
    private String dataMovimento;
    
    @Column(name = "tx_motivo_selo_verde")
    private String txMotivoSeloVerde;
    
    @Column(name = "cd_status_material")
    private Long cdStatusMaterial;
    
    @Column(name = "cd_agrupamento")
    private Long cdAgrupamento;
    
	@ManyToOne
	@JoinColumn(name="cd_classe", nullable=true, insertable=false, updatable=false)
	private MaterialClasse materialClasse;
    

}
