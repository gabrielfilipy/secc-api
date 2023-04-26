package com.secc.api.integracao.siga.enm;

import lombok.Getter;

@Getter
public enum SigaExEnm {

	URI_DOCUMENTO("/sigaex/api/v1/documentos"),
	URI_DOWNLOAD("/sigaex/api/v1/download"),
	URI_ASSINAR_CERTIFICADO_DIGITAL("/sigaex/app/assinador-popup/doc");
	
	private String desc;
	
	private SigaExEnm(String desc) {
		this.desc = desc; 
	}
	
}
