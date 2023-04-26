package com.secc.api.integracao.siga;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.secc.api.domain.exception.NotFoundDpPessoaException;
import com.secc.api.domain.exception.RegraDeNegocioException;
import com.secc.api.integracao.siga.config.interceptor.Interceptador;
import com.secc.api.integracao.siga.enm.SIGA_ACAO;
import com.secc.api.integracao.siga.enm.SigaEnm;
import com.secc.api.integracao.siga.enm.SigaExEnm;
import com.secc.api.integracao.siga.model.ExDocumento;

@Component
public class SigaModuloDocumental extends Interceptador {

	@Value("${tipo.ambiente.siga}") 
	private String AMBIENTE;
	
	@Autowired
	SigaAutenticador siga;
	
	public ExDocumento finalizarDocumento(String siglaMobil, String token) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			HttpRequest request = HttpRequest.newBuilder()
					.POST(BodyPublishers.ofString(""))
					.uri(URI.create(
							AMBIENTE 
							+ SigaExEnm.URI_DOCUMENTO.getDesc()
							+ "/"
							+ siglaMobil
							+ SIGA_ACAO.FINALIZAR.getDesc()
							)
						)
					.header("Content-Type", SigaEnm.ACCEPT.getDesc())
					.headers("Authorization", token)
					.timeout(Duration.ofSeconds(25))   
					.build();
			
			HttpClient client = HttpClient.newBuilder()
					.followRedirects(Redirect.NORMAL)
					.connectTimeout(Duration.ofSeconds(25))
					.build(); 
			
			HttpResponse<String> response;
			response = client.send(request, BodyHandlers.ofString());
			String json = response.body();
			ExDocumento obj = objectMapper.readValue(json, ExDocumento.class);
			
			return obj;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RegraDeNegocioException(e.getMessage());
		}
	}
	
	public Object criarNovoDocumento(ExDocumento exDocumento) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String body = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(exDocumento);
			HttpRequest request = HttpRequest.newBuilder()
					.POST(BodyPublishers.ofString(body))
					.uri(URI.create(
							AMBIENTE 
							+ SigaExEnm.URI_DOCUMENTO.getDesc())
						)
					.header("Content-Type", SigaEnm.ACCEPT.getDesc())
					.headers("Authorization", getAuthorization())
					.timeout(Duration.ofSeconds(25))   
					.build();
			
			HttpClient client = HttpClient.newBuilder()
					.followRedirects(Redirect.NORMAL)
					.connectTimeout(Duration.ofSeconds(25))
					.build(); 
			
			HttpResponse<String> response;
			response = client.send(request, BodyHandlers.ofString());
			String json = response.body();
			Object obj = objectMapper.readValue(json, Object.class);
			cleanAutorization();
			return obj;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RegraDeNegocioException(e.getMessage());
		}
	}
	
	public Object incluirCossignatario(String siglaMobil, String matricula, 
			String funcao, String lotacao, String localidade, String token) {
		try {
			HttpRequest request;
			request = HttpRequest.newBuilder()
					.POST(BodyPublishers.ofString(""))
					.uri(URI.create(AMBIENTE 
							+ SigaEnm.URI_DOCUMENTO.getDesc() + "/"
							+ siglaMobil 
							+ SIGA_ACAO.INCLUIR_COSSIGNATARIO.getDesc() 
							+ "?matricula=" + matricula 
							+ "&funcao=" + funcao 
							+ "&lotacao=" + lotacao 
							+ "&localidade=" + localidade)) 
					.header("accept", SigaEnm.ACCEPT.getDesc())
					.headers("Authorization", token)
					.timeout(Duration.ofSeconds(25))   
					.build();
		
			HttpClient client = HttpClient.newBuilder()
					.followRedirects(Redirect.NORMAL)
					.connectTimeout(Duration.ofSeconds(25))
					.build(); 
			
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			String json = response.body();
			
			ObjectMapper objectMapper = new ObjectMapper();
			Object obj = objectMapper.readValue(json, Object.class);
			
			if(response.statusCode() == 404) 
				throw new NotFoundDpPessoaException(null, matricula);
			
			if(response.statusCode() == 500) 
				throw new RegraDeNegocioException(json);
			
			return obj; 
		
		} catch (Exception e) {
			throw new RegraDeNegocioException(e.getMessage()); 
		} 
		
	}

	
}
