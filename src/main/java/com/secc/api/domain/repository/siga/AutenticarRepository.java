package com.secc.api.domain.repository.siga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.secc.api.integracao.siga.SigaAutenticador;

@Repository
public class AutenticarRepository {

	@Autowired
	private SigaAutenticador sigaAutenticador; 
	
	public Object autenticarNoSiga() throws Exception {
		return sigaAutenticador.autenticador();
	}
	
}
