package com.secc.api.domain.repository;

import java.util.List;

import com.secc.api.domain.model.Servico;

public interface ServicoRepositoryQueries {

	List<Servico> pesquisarPorDesc(String txDescricaoItem);

	List<Servico> pesquisarPorCodigo(String cdItem);

}
