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
@Table(name = "material_grupo")
public class MaterialGrupo {

	@Id
	@Column(name = "cd_grupo")
    private Long cdGrupo;

	@Column(name = "tx_nome_grupo")
    private String txNomeGrupo;
    
    @Column(name = "data_alteracao")
    private String dataAlteracao;
    
    @Column(name = "data_movimento")
    private String dataMovimento;
    
    @Column(name = "cd_status_grupo")
    private Long cdStatusGrupo;
   
}
