package com.secc.api.integracao.siga;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.secc.api.domain.exception.NotFoundDpPessoaException;
import com.secc.api.integracao.siga.config.Serializador;
import com.secc.api.integracao.siga.config.interceptor.Interceptador;
import com.secc.api.integracao.siga.enm.SigaEnm;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class SigaAutenticador extends Interceptador{
	
	public static final String ERROR_FAIL_SIGA = "Unrecognized SSL message, plaintext connection?";
	
	@Value("${tipo.ambiente.siga}")
	private String AMBIENTE;
	
	private String token;
	
	public SigaAutenticador() { }
	
	public Object autenticador() throws Exception {
		try {
			HttpRequest request = HttpRequest.newBuilder()
					.POST(BodyPublishers.ofString(""))
					.uri(URI.create(AMBIENTE + SigaEnm.URI_AUTENTICACAO.getDesc()))
					.header("accept", SigaEnm.ACCEPT.getDesc())
					.headers("Authorization", getAuthorization())
					.timeout(Duration.ofSeconds(25))  
					.build();
			
			HttpClient client = HttpClient.newBuilder()
					.followRedirects(Redirect.NORMAL)
					.connectTimeout(Duration.ofSeconds(25))
					.build(); 
			
			HttpResponse<String> response;
			response = client.send(request, BodyHandlers.ofString());
			
			String objetoJson = response.body();
			
			Serializador.recuperaErro(objetoJson);
			
			ObjectMapper objectMapper = new ObjectMapper();
			Object obj = objectMapper.readValue(objetoJson, Object.class);
			cleanAutorization();
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage().equals(ERROR_FAIL_SIGA))
				throw new Exception("Siga fora do ar: " + e.getMessage());
				
			throw new NotFoundDpPessoaException(e.getMessage());
		}
	}
		
}
