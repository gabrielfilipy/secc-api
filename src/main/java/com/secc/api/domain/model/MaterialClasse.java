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
@Table(name = "material_classe")
public class MaterialClasse {
    
	@Id
	@Column(name = "cd_grupo")
    private Long cdGrupo;

	@Column(name = "cd_classe")
    private Long cdClasse;
    
	@Column(name = "data_alteracao")
    private String dataAlteracao;
    
    @Column(name = "data_movimento")
    private String dataMovimento;
    
    @Column(name = "tx_nota_grupo")
    private String txNotaGrupo;
    
    @Column(name = "cd_status_classe")
    private Long cdStatusClasse;
    
	@ManyToOne
	@JoinColumn(name="cd_classe", nullable=true, insertable=false, updatable=false)
	private MaterialGrupo materialGrupo;
	
}
