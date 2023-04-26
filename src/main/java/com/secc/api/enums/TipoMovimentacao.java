package com.secc.api.enums;

import lombok.Getter;

@Getter
public enum TipoMovimentacao {

	INCLUIR_COSSIGNATARIO("Incluir cossignatário", 1);
	
	private String desc;
	private Integer cod;
	
	private TipoMovimentacao(String desc, Integer cod) {
		this.desc = desc; 
		this.cod = cod;
	}
	
}
