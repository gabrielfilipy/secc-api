package com.secc.api.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.secc.api.domain.model.Movimentacao;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>,
	MovimentacaoRepositoryQueries {

	@Query("from Movimentacao m where m.documento.sigla = :siglaDocumento")
	List<Movimentacao> buscarMovimentacoesDoDocumento(String siglaDocumento); 
	
	@Query("from Movimentacao m where documento.sigla = :siglaDocumento "
			+ "and siglaSubscritor = :siglaSubscritor "
			+ "and tipoMovimentacao = :tipoMovimentacao "
			+ "and dataFinalizacao IS NULL")
	Movimentacao buscaMovimentacaoFinalizada(String siglaDocumento, String siglaSubscritor,
			Integer tipoMovimentacao);   
	
	
}
