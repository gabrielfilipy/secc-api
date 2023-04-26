package com.secc.api.integracao.siga;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.secc.api.domain.exception.RegraDeNegocioException;
import com.secc.api.integracao.siga.config.Serializador;
import com.secc.api.integracao.siga.config.interceptor.Interceptador;
import com.secc.api.integracao.siga.enm.SIGA_ACAO;
import com.secc.api.integracao.siga.enm.SigaEnm;
import com.secc.api.integracao.siga.enm.SigaExEnm;
import com.secc.api.integracao.siga.model.Certificado;

@Component
public class SigaAssinador extends Interceptador {

	@Value("${tipo.ambiente.siga}")
	private String AMBIENTE;
	
	@Autowired
	SigaAutenticador siga;
	
	public Object assinadorPopupHash(String sigla) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			
			HttpRequest request = HttpRequest.newBuilder() 
					.POST(BodyPublishers.ofString(""))
					.uri(URI.create(
							AMBIENTE 
							+ SigaExEnm.URI_ASSINAR_CERTIFICADO_DIGITAL.getDesc() 
							+ "/" + sigla 
							+ SIGA_ACAO.HASH.getDesc())
						)
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
			String json = response.body();
			Object obj = objectMapper.readValue(json, Object.class);
			cleanAutorization();
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RegraDeNegocioException(e.getMessage());
		}
	}
	
	public Object assinadorPopupSave(Certificado certificado, String codigoDocumento) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String body = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(certificado);
			HttpRequest request = HttpRequest.newBuilder() 
					.PUT(BodyPublishers.ofString(body)) 
					.uri(URI.create(
							AMBIENTE 
							+ SigaExEnm.URI_ASSINAR_CERTIFICADO_DIGITAL.getDesc() 
							+ "/" + codigoDocumento 
							+ SIGA_ACAO.SIGN.getDesc())
						)
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
			String json = response.body();
			
			Object obj = objectMapper.readValue(json, Object.class);
			cleanAutorization();
			return obj;
	
		} catch (Exception e) {
			e.printStackTrace();
			throw new RegraDeNegocioException(e.getMessage());
		}
		
	}
	
	public Object assinarDocumentoComSenha(String siglaMobil, String token) {
		try {
			HttpRequest request = HttpRequest.newBuilder() 
					.POST(BodyPublishers.ofString(""))
					.uri(URI.create(
							AMBIENTE 
							+ SigaExEnm.URI_DOCUMENTO.getDesc() 
							+ "/" + siglaMobil 
							+ SIGA_ACAO.ASSINAR_COM_SENHA.getDesc())
						)
					.header("accept", SigaEnm.ACCEPT.getDesc())
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
			ObjectMapper objectMapper = new ObjectMapper();
			Object obj = objectMapper.readValue(json, Object.class);
			
			if(response.statusCode() == 500)
				Serializador.recuperaErro(json); 
			
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RegraDeNegocioException(e.getMessage());
		}	
	}
	
}
