package com.secc.api.domain.service.siga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secc.api.domain.repository.siga.AutenticarRepository;

@Service
public class AutenticarService {

	@Autowired
	private AutenticarRepository repository;
	
	public Object autenticarUsuario() throws Exception {
		return repository.autenticarNoSiga();
	}
	
}
