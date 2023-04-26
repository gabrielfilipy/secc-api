package com.secc.api.domain.exception;

public class RegraDeNegocioException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public RegraDeNegocioException(String message) {
		super(message);
	}
	
	public RegraDeNegocioException(String messsage, Throwable cases) {
		super(messsage, cases);
	}
	
}
