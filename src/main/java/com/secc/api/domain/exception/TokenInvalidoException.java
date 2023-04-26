package com.secc.api.domain.exception;

import org.hibernate.service.spi.ServiceException;

public class TokenInvalidoException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public TokenInvalidoException(String message) {
		super(message);
	}
	
}
