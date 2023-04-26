package com.secc.api.domain.exception;

public class NotFoundMovimentacaoException extends EntidadeNaoExisteException{

	private static final long serialVersionUID = 1L;

	public NotFoundMovimentacaoException(String message) {
		super(message);
	}

	public NotFoundMovimentacaoException(Long codigo, String sigla) { 
		this("A movimentacação com o codigo (" + codigo + ") não existe.");
	}
	
}
