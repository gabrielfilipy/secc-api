package com.secc.api.domain.exception;

public class NotFoundDocumentoException extends EntidadeNaoExisteException{

	private static final long serialVersionUID = 1L;

	public NotFoundDocumentoException(String message) {
		super(message);
	}

	public NotFoundDocumentoException(Long codigo, String sigla) { 
		this("O documento com o codigo (" + codigo + ") não existe.");
	}
	
}