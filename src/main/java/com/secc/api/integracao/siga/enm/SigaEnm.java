package com.secc.api.integracao.siga.enm;

import lombok.Getter;

@Getter
public enum SigaEnm {
	
	URI_AUTENTICACAO("/siga/api/v1/autenticar"),
	URI_PESSOA("/siga/api/v1/pessoas"),
	URI_MESA_VIRTUAL("/sigaex/api/v1"),
	URI_DOCUMENTO("/sigaex/api/v1/documentos"),
	ACCEPT("application/json"),
	PDF("application/pdf");
	
	private String desc;
	
	private SigaEnm(String desc) {
		this.desc = desc; 
	}
	
}
