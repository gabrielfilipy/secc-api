package com.secc.api.domain.repository.siga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.secc.api.exceptionhandler.validate.ExDocumentoValidate;
import com.secc.api.integracao.siga.SigaModuloDocumental;
import com.secc.api.integracao.siga.config.interceptor.Interceptador;
import com.secc.api.integracao.siga.model.ExDocumento;

@Repository
public class DocumentoSigaRepository extends Interceptador {

	@Autowired
	private SigaModuloDocumental sigaModuloDocumental;
	
	@Autowired
	private ExDocumentoValidate validate;
	
	public Object criarDocumentoNoSiga(ExDocumento exDocumento) {
		validate.validarCampos(exDocumento);
		return sigaModuloDocumental.criarNovoDocumento(exDocumento);
	}
	
	public Object incluirCossignatario(String siglaMobil, String matricula, 
			String funcao, String lotacao, String localidade, String token) {
		return sigaModuloDocumental.incluirCossignatario(siglaMobil, matricula, funcao, lotacao, localidade, token);
	}
	
	public ExDocumento finalizarDocumento(String siglaMobil, String token) {
		return sigaModuloDocumental.finalizarDocumento(siglaMobil, token);
	}
	
}
