package com.secc.api.domain.repository.siga;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.secc.api.integracao.siga.SigaDpPessoa;
import com.secc.api.integracao.siga.model.DpPessoa;

@Repository
public class PessoaRepository {

	@Autowired
	private SigaDpPessoa sigaDpPessoa; 
	
	public List<DpPessoa> listar(String texto) throws IOException, InterruptedException {
		return sigaDpPessoa.listarPessoa(texto);
	} 
	
	public DpPessoa buscar(String sigla) throws Exception {
		return sigaDpPessoa.buscarPessoa(sigla);
	} 
	
}