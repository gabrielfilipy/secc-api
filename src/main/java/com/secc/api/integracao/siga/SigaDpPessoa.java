package com.secc.api.integracao.siga;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.secc.api.domain.exception.NotFoundDpPessoaException;
import com.secc.api.domain.exception.RegraDeNegocioException;
import com.secc.api.integracao.siga.config.Serializador;
import com.secc.api.integracao.siga.config.interceptor.Interceptador;
import com.secc.api.integracao.siga.enm.SigaEnm;
import com.secc.api.integracao.siga.model.DpPessoa;

@Component
public class SigaDpPessoa extends Interceptador{

	@Value("${tipo.ambiente.siga}")
	private String AMBIENTE;
	
	@Autowired
	SigaAutenticador siga;
	
	public List<DpPessoa> listarPessoa(String texto) { 
		try {
			HttpRequest request = HttpRequest.newBuilder()
					.GET()
					.uri(URI.create(AMBIENTE + SigaEnm.URI_PESSOA.getDesc() + "?texto=" + texto))
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
			cleanAutorization();
			return Serializador.jsonParaColecaoObjetoJava(json);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RegraDeNegocioException(e.getMessage());
		}
		
	}
	
	public DpPessoa buscarPessoa(String sigla) throws Exception {
		HttpRequest request = HttpRequest.newBuilder()
				.GET()
				.uri(URI.create(
						AMBIENTE 
						+ SigaEnm.URI_PESSOA.getDesc() 
						+ "/" 
						+ sigla)
					) 
				.header("accept", SigaEnm.ACCEPT.getDesc())
				.headers("Authorization", getAuthorization())
				.timeout(Duration.ofSeconds(50))   
				.build();
			
		HttpClient client = HttpClient.newBuilder()
				.followRedirects(Redirect.NORMAL)
				.connectTimeout(Duration.ofSeconds(50))
				.build(); 
			
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String json = response.body();
			
		if(response.statusCode() == 404) 
			throw new NotFoundDpPessoaException(null, sigla);
			
		Serializador.recuperaErro(json);
			 
		return Serializador.jsonParaObjetoJava(json); 
		
	}
	
}
