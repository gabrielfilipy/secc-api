package com.secc.api.infrastruct.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.secc.api.domain.model.Documento;
import com.secc.api.domain.repository.DocumentoRepositoryQueries;
import com.secc.api.domain.repository.siga.PessoaRepository;

@Repository
public class DocumentoRepositoryImpl implements DocumentoRepositoryQueries {

	@PersistenceContext 
	private EntityManager manager;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Override
	public Page<Documento> carregarDocumentos(String siglaCadastrante, Boolean dataAssinatura, Boolean dataExclusao,
			int pageIndex, int pageSize) throws Exception {
		
		pessoaRepository.buscar(siglaCadastrante);
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Documento> criteria = builder.createQuery(Documento.class);
		
		Root<Documento> root = criteria.from(Documento.class);
		
		Predicate[] predicates = criarRestricoes(siglaCadastrante, dataAssinatura, dataExclusao, builder, root);
		
		criteria.where(predicates);
		if(!dataAssinatura) 
			criteria.orderBy(builder.desc(root.get("dataCriacao")));
		else
			criteria.orderBy(builder.desc(root.get("dataAssinatura")));
			
		TypedQuery<Documento> query = manager.createQuery(criteria);
		
		adicionarRestricoesDePaginacao(query, pageIndex, pageSize);
		 
		return new PageImpl<>(query.getResultList(), PageRequest.of(pageIndex, pageSize), total(siglaCadastrante, dataAssinatura, dataExclusao));
	}
	
	private Predicate[] criarRestricoes(String siglaCadastrante, Boolean dataAssinatura, Boolean dataExclusao, 
			CriteriaBuilder builder, Root<Documento> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(siglaCadastrante))
			predicates.add(builder.equal(root.get("cadastrante"), siglaCadastrante));
		
		if(dataAssinatura)
			predicates.add(builder.isNotNull(root.get("dataAssinatura")));
		else
			predicates.add(builder.isNull(root.get("dataAssinatura")));
		
		if(dataExclusao)
			predicates.add(builder.isNotNull(root.get("dataExclusao")));
		else 
			predicates.add(builder.isNull(root.get("dataExclusao")));
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void adicionarRestricoesDePaginacao(TypedQuery<Documento> query, int pageIndex, int pageSize) {
		int paginaAtual = pageIndex;
		int totalRegistrosPorPagina = pageSize;
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	private Long total(String siglaCadastrante, Boolean dataAssinatura, Boolean dataExclusao) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Documento> root = criteria.from(Documento.class);
		
		Predicate[] predicates = criarRestricoes(siglaCadastrante, dataAssinatura, dataExclusao, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
	
}
