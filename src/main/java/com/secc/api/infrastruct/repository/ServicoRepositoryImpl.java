package com.secc.api.infrastruct.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.secc.api.domain.model.Servico;
import com.secc.api.domain.repository.ServicoRepositoryQueries;

@Repository
public class ServicoRepositoryImpl implements ServicoRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Servico> pesquisarPorDesc(String desc) {
	  	
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Servico> query = cb.createQuery(Servico.class);
        Root<Servico> material = query.from(Servico.class);

        query.select(material).where(cb.like(material.get("txDescricaoItem"), "%" + desc + "%"));
        
        List<Servico> listaMaterial = manager.createQuery(query).getResultList();

        return listaMaterial;
	}
	/*
	 * CriteriaBuilder cb = manager.getCriteriaBuilder(); CriteriaQuery<Servico>
	 * query = cb.createQuery(Servico.class); Root<Servico> servico =
	 * query.from(Servico.class);
	 * 
	 * List<Predicate> predicates = new ArrayList();
	 * 
	 * if(StringUtils.hasText(desc)) {
	 * predicates.add(cb.like(servico.get("txDescricaoItem"), "%" + desc + "%")); }
	 * 
	 * query.select(servico).where(predicates.toArray(new Predicate[0]));
	 * 
	 * var listaServico = manager.createQuery(query);
	 * 
	 * return listaServico.getResultList(); }
	 */
	@Override
	public List<Servico> pesquisarPorCodigo(String codigo) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Servico> query = cb.createQuery(Servico.class);
        Root<Servico> servico = query.from(Servico.class);

        query.select(servico).where(cb.equal(servico.get("cdServico"), codigo));
        
        List<Servico> listaServico = manager.createQuery(query).getResultList();

        return listaServico;
	}

}
