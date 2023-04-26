package com.secc.api.integracao.siga.config;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.secc.api.domain.exception.RegraDeNegocioException;
import com.secc.api.integracao.siga.model.DpPessoa;

@Configuration
public class Serializador {

	public static List<DpPessoa> jsonParaColecaoObjetoJava(String json)  {
		DpPessoa pessoa = null;
		JSONObject obj=null;
		List<DpPessoa> pessoas = null;
		
		try {
			obj = new JSONObject(json);
			pessoas = new ArrayList<>();
			JSONArray list = obj.getJSONArray("list");
			for(int i = 0; i < list.length(); i++) {
				pessoa = new DpPessoa();
				
                JSONObject o = list.getJSONObject(i);
                pessoa.setNome(o.getString("nome"));
                
                if(o.has("email")) 
                	pessoa.setEmail(o.getString("email"));
                
                if(o.has("idPessoaIni")) 
                	pessoa.setIdPessoaIni(o.getString("idPessoaIni"));
                
                if(o.has("isExternaPessoa")) 
                	pessoa.setIsExternaPessoa(o.getBoolean("isExternaPessoa")); 
                
                if(o.has("sigla")) 
                	pessoa.setSigla(o.getString("sigla"));
                
                pessoa.setSiglaLotacao(o.getJSONObject("lotacao").getString("sigla"));
                
                pessoas.add(pessoa);
               
            }
			
		} catch (Exception e) { 
			System.out.println("Error: " + e.getMessage());
		}
		
		return pessoas;
		
	}
	
	public static DpPessoa jsonParaObjetoJava(String json) {
		Gson gson = new Gson();
		DpPessoa dpPessoa = new DpPessoa();
		try {
			JSONObject obj = new JSONObject(json);
			if(obj.has("pessoa")) {
				JSONObject pessoaJson = obj.getJSONObject("pessoa");
				dpPessoa = gson.fromJson(pessoaJson.toString(), DpPessoa.class);
			} 
		} catch (Exception e) { 
			e.printStackTrace();
			
			throw new RegraDeNegocioException("Erro : " + e.getMessage());
		}
		
		return dpPessoa;
		
	}
	
	public static String decodeTokenJwtAssinador(String tokenAssinante) {
		String[] chunks = tokenAssinante.split("\\.");
		Base64.Decoder decoder = Base64.getUrlDecoder();
		String payload = new String(decoder.decode(chunks[1]));
		JSONObject obj = new JSONObject(payload);
		
		String assinador = obj.getString("sub");
		
		return assinador;
	}
	
	public static void recuperaErro(String json) {
		JSONObject obj = new JSONObject(json);
		if (obj.has("errormsg")) {
			throw new RegraDeNegocioException(obj.getString("errormsg"));		}
	}
	
}
