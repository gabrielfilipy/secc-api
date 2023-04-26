package com.secc.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.secc.api.domain.model.Documento;

@Repository
public interface DocumentoRepository 
	extends JpaRepository<Documento, Long>, DocumentoRepositoryQueries {

	Documento findBySigla(String sigla); 
	
	@Query("from Documento where id = :id and data_exclusao IS NULL")
	Documento buscarDocumentoValidoPeloId(Long id);
	
}