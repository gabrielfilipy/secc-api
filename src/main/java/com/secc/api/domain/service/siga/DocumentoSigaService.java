package com.secc.api.domain.service.siga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secc.api.domain.repository.siga.DocumentoSigaRepository;
import com.secc.api.integracao.siga.config.interceptor.Interceptador;
import com.secc.api.integracao.siga.model.ExDocumento;

@Service
public class DocumentoSigaService extends Interceptador {
	
	@Autowired
	private DocumentoSigaRepository repository; 
	
	public Object criarUmNovoDocumento(ExDocumento exDocumento) {
		return repository.criarDocumentoNoSiga(exDocumento);
	}
	
	public Object incluirCossignatario(String siglaMobil, String matricula, 
			String funcao, String lotacao, String localidade, String token) {
		return repository.incluirCossignatario(siglaMobil, matricula, funcao, lotacao, localidade, token);
	}
	
	public ExDocumento finalizarDocumento(String siglaMobil, String token) {
		return repository.finalizarDocumento(siglaMobil, token);
	}
	
}
