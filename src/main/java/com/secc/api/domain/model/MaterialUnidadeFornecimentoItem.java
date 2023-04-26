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
@Table(name = "material_unidade_fornecimento_item")
public class MaterialUnidadeFornecimentoItem {
	    
	@Id
	@Column(name = "cd_item")
    private Integer cdItem;

	@Column(name = "cd_nr_unidade_fornec")
    private Integer cdNrUnidadeFornec;
    
	@Column(name = "tx_unidade_completa")
    private String txUnidadeCompleta;
    
    @Column(name = "tx_status")
    private String txStatus;
    
    @Column(name = "cd_bec")
    private Integer cdBec;
    
    @Column(name = "data_alteracao")
    private String dataAlteracao;
}
