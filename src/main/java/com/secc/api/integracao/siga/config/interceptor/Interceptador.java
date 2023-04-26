package com.secc.api.integracao.siga.config.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.secc.api.domain.exception.RegraDeNegocioException;

import lombok.Setter;

/**
 * Essa classe tem a responsabilidade de interceptar
 * informações da requisição.
 */

@Setter
@Component
public class Interceptador implements HandlerInterceptor {

	static String authorization;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String headers = request.getHeader("Authorization");
		
		if(headers == null) 
			throw new RegraDeNegocioException("É preciso informar um token do Siga.");
		
		authorization = headers;
		
	    return true;
	}
	
	public static String getAuthorization() {
		return authorization;
	}
	
	public static void cleanAutorization() {
		authorization = "access danied";
	}
	 
}
