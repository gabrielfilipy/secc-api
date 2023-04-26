package com.secc.api.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.secc.api.domain.model.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long>, ServicoRepositoryQueries {
	
	Servico findByCdServico(String cdServico); 
	
	List<Servico> findByTxDescricaoItemLike(String txDescricaoItem);

}
