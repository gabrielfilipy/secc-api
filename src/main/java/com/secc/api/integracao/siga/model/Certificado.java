package com.secc.api.integracao.siga.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Certificado {

	private String envelope; 
	private String time;
	private String policy;
	private String policyversion;
	private String sha1;
	private String sha256;
	private String certificate;
	private String code;
	private String cpf;
	
}
