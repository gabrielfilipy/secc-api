package com.secc.api.domain.service.siga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secc.api.domain.repository.siga.SigaAssinadorRepository;
import com.secc.api.integracao.siga.config.interceptor.Interceptador;
import com.secc.api.integracao.siga.model.Certificado;

@Service
public class SigaAssinadorService extends Interceptador {

	@Autowired
	private SigaAssinadorRepository repository;
	
	public Object criarHash(String sigla) {
		return repository.assinadorPopupHash(sigla);
	}
	
	public Object salvarAssinatura(Certificado certificado, String codigoDocumento) {
		return repository.assinadorPopupSave(certificado, codigoDocumento);
	}
	
	public Object assinarDocumentoComSenha(String sigla, String token) {
		return repository.assinadorComSenha(sigla, token);
	}
	
}
