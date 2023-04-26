package com.secc.api.integracao.siga.enm;

import lombok.Getter;

@Getter
public enum SigaMarcasEnm {

	EM_ELABORACAO(1, "Em Elaboração", "fas fa-lightbulb"); 
	
	private int codigo;
	private String desc;
	private String icon;
	
	private SigaMarcasEnm(int codigo, String desc, String icon) {
		this.codigo = codigo;
		this.desc = desc; 
		this.icon = icon;
	}
	
}
