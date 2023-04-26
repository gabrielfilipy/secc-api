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
@Table(name = "material_item_caracteristica")
public class MaterialItemCaracteristica {
	    
	@Id
	@Column(name = "cd_item")
    private Long cdItem;

	@Column(name = "cd_item_seq_caracteristica")
    private Long cdItemSeqCaracteristica;
    
	@Column(name = "tx_descricao_caracteristica")
    private String txDescricaoCaracteristica;
    
    @Column(name = "tx_descricao_caracteristica_prefixo")
    private String txDescricaoCaracteristicaPrefixo;
    
    @Column(name = "nr_obrigatorio")
    private Long nrObrigatorio;
    
    @Column(name = "nr_sequencia_caracteristica")
    private Long nrSequenciaCaracteristica;
	
}
