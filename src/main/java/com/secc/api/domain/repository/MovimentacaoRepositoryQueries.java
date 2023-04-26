package com.secc.api.domain.repository;

import java.time.OffsetDateTime;
import java.util.List;

import com.secc.api.domain.model.Movimentacao;

public interface MovimentacaoRepositoryQueries {

	public List<Movimentacao> movimentacoes(String subscritor, OffsetDateTime dataCriacao, 
			Integer tipoMovimentacao);
	
	public List<Movimentacao> movimentacoes(String siglaDocumento, Integer tipoMovimentacao);
	
}
