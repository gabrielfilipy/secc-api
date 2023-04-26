package com.secc.api.domain.repository;

import java.util.List;

import com.secc.api.domain.model.Material;

public interface MaterialRepositoryQueries {

	List<Material> pesquisarPorDesc(String txDescricaoItem);

	List<Material> pesquisarPorCodigo(Integer cdMaterial);

}
