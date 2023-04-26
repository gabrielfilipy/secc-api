package com.secc.api.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Catalogo {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

	private long idModelo;
	
    @Column(nullable = false)
    private Integer codItem;

    @Column(nullable = false)
    private Integer quantidade;
    
    @Column(nullable = false)
    private String tpSolicitacao;
    
    @Column(nullable = false)
    private String unidadeFornecimento;
    
    @Column(nullable = false)
    private String descricao;
    
    @Column(nullable = false)
    private String acao;
	
}
