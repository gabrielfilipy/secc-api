package com.secc.api.domain.repository;

import org.springframework.data.domain.Page;

import com.secc.api.domain.model.Documento;

public interface DocumentoRepositoryQueries {

	Page<Documento> carregarDocumentos(String siglaCadastrante, Boolean dataAssinatura, Boolean dataExclusao, 
			int pageIndex, int pageSize) throws Exception; 
	
}