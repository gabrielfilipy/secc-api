package com.secc.api.infrastruct.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.secc.api.domain.model.Material;
import com.secc.api.domain.repository.MaterialRepositoryQueries;

@Repository
public class MaterialRepositoryImpl implements MaterialRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	
	  @Override 
	  public List<Material> pesquisarPorDesc(String desc) {
		  	
	        CriteriaBuilder cb = manager.getCriteriaBuilder();
	        CriteriaQuery<Material> query = cb.createQuery(Material.class);
	        Root<Material> material = query.from(Material.class);

	        query.select(material).where(cb.like(material.get("txDescricaoItem"), "%" + desc + "%"));
	        
	        List<Material> listaMaterial = manager.createQuery(query).getResultList();

	        return listaMaterial;
		}
	 

	@Override
	public List<Material> pesquisarPorCodigo(Integer cdItem) {

	  	
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Material> query = cb.createQuery(Material.class);
        Root<Material> material = query.from(Material.class);

        query.select(material).where(cb.equal(material.get("cdMaterial"), cdItem));
        
        List<Material> listaMaterial = manager.createQuery(query).getResultList();

        return listaMaterial;
	}

}
