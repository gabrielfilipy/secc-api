package com.secc.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.secc.api.domain.model.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long>, MaterialRepositoryQueries {

	Material findByTxDescricaoItem(String txDescricaoItem); 
}
