package com.secc.api.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.secc.api.domain.model.TextoPadrao;

@Repository
public interface TextoPadraoRepository extends JpaRepository<TextoPadrao, Long> {

	@Query("from TextoPadrao txt where txt.siglaPessoa = :siglaPessoa and txt.modelo = :modelo")
	List<TextoPadrao> buscarColecaoDeTextos(String siglaPessoa, String modelo);
	
}
