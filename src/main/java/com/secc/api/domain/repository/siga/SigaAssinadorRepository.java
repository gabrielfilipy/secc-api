package com.secc.api.domain.repository.siga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.secc.api.integracao.siga.SigaAssinador;
import com.secc.api.integracao.siga.config.interceptor.Interceptador;
import com.secc.api.integracao.siga.model.Certificado;

@Repository
public class SigaAssinadorRepository extends Interceptador {

	@Autowired
	private SigaAssinador sigaAssinador;
	
	public Object assinadorComSenha(String sigla, String token) {
		return sigaAssinador.assinarDocumentoComSenha(sigla, token);
	}
	
	public Object assinadorPopupHash(String sigla) {
		return sigaAssinador.assinadorPopupHash(sigla);
	}
	
	public Object assinadorPopupSave(Certificado certificado, String codigoDocumento) {
		return sigaAssinador.assinadorPopupSave(certificado, codigoDocumento);
	}
	
}
