package com.secc.api.exceptionhandler.validate;

import org.springframework.stereotype.Component;

import com.secc.api.domain.exception.RegraDeNegocioException;
import com.secc.api.integracao.siga.model.ExDocumento;

@Component
public class ExDocumentoValidate {

	public void validarCampos(ExDocumento doc) {
		
		if(doc.getClassificacao() != null && doc.getClassificacao().isBlank()) 
			throw new RegraDeNegocioException("O campo 'classificacao' não pode ficar em branco.");
		
		if(doc.getDescricaodocumento() != null && doc.getDescricaodocumento().isBlank()) 
			throw new RegraDeNegocioException("O campo 'descricaodocumento' não pode ficar em branco.");
		
		if(doc.getDescricaotipodoc() != null && doc.getDescricaotipodoc().isBlank()) 
			throw new RegraDeNegocioException("O campo 'descricaotipodoc' não pode ficar em branco.");
		
		if(doc.getDestinatariocampoextra() != null && doc.getDestinatariocampoextra().isBlank()) 
			throw new RegraDeNegocioException("O campo 'destinatariocampoextra' não pode ficar em branco.");
		
		if(doc.getLotadestinatario() != null && doc.getLotadestinatario().isBlank()) 
			throw new RegraDeNegocioException("O campo 'lotadestinatario' não pode ficar em branco.");
		
		if(doc.getModelo() == null && doc.getModelo().isBlank()) 
			throw new RegraDeNegocioException("O campo 'modelo' não pode ser nulo e nem ficar em branco.");
		
		if(doc.getNivelacesso() != null && doc.getNivelacesso().isBlank()) 
			throw new RegraDeNegocioException("O campo 'nivelacesso' não pode ficar em branco.");
		
		if(doc.getOrgaoexternodestinatario() != null && doc.getOrgaoexternodestinatario().isBlank()) 
			throw new RegraDeNegocioException("O campo 'orgaoexternodestinatario' não pode ficar em branco.");
		
		if(doc.getPessoadestinatario() != null && doc.getPessoadestinatario().isBlank()) 
			throw new RegraDeNegocioException("O campo 'pessoadestinatario' não pode ficar em branco.");
		
		if(doc.getSigla() != null && doc.getSigla().isBlank()) 
			throw new RegraDeNegocioException("O campo 'sigla' não pode ficar em branco.");
		
		if(doc.getSigladoc() != null && doc.getSigladoc().isBlank()) 
			throw new RegraDeNegocioException("O campo 'siglaDoc' não pode ficar em branco.");
		
		if(doc.getSiglamobilfilho() != null && doc.getSiglamobilfilho().isBlank()) 
			throw new RegraDeNegocioException("O campo 'siglamobilfilho' não pode ficar em branco.");
		
		if(doc.getSiglamobilpai() != null && doc.getSiglamobilpai().isBlank()) 
			throw new RegraDeNegocioException("O campo 'siglamobilpai' não pode ficar em branco.");
		
		if(doc.getSubscritor() == null || doc.getSubscritor().isBlank()) 
			throw new RegraDeNegocioException("O campo 'subscritor' não pode ser nulo e nem ficar em branco.");
		
		if(doc.getTitular() != null && doc.getTitular().isBlank()) 
			throw new RegraDeNegocioException("O campo 'titular' não pode ficar em branco.");
		
	}
	
}
