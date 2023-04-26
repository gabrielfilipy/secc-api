package com.secc.api.integracao.siga.enm;

import lombok.Getter;

@Getter
public enum SIGA_ACAO {

	FINALIZAR("/finalizar"),
	ASSINAR_COM_SENHA("/assinar-com-senha"),
	EXCLUIR("/excluir"),
	SIGN("/sign"),
	HASH("/hash"),
	INCLUIR_COSSIGNATARIO("/incluir-cossignatario"),
	AUTENTICAR_SENHA("autenticar-com-senha"),
	PDF_COMPLETO("/pdf-completo"),
	ARQUIVO("/arquivo");
	
	private String desc;
	
	private SIGA_ACAO(String desc) {
		this.desc = desc; 
	}
	
}
