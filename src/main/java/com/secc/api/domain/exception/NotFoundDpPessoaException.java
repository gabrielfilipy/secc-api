package com.secc.api.domain.exception;

public class NotFoundDpPessoaException extends EntidadeNaoExisteException{

	private static final long serialVersionUID = 1L;

	public NotFoundDpPessoaException(String message) {
		super(message);
	}

	public NotFoundDpPessoaException(Long codigo, String sigla) { 
		this("A pessoa informada com a matrícula (" + sigla + ") não existe.");
	}
	
}
