package com.secc.api.infrastruct.repository;

import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.secc.api.domain.exception.NotFoundDocumentoException;
import com.secc.api.domain.model.Documento;
import com.secc.api.domain.model.Movimentacao;
import com.secc.api.domain.repository.DocumentoRepository;
import com.secc.api.domain.repository.MovimentacaoRepositoryQueries;

@Repository
public class MovimentacaoRepositoryImpl implements MovimentacaoRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired 
	private DocumentoRepository documentoRepository;
	
	@Override
	public List<Movimentacao> movimentacoes(String siglaSubscritor, OffsetDateTime dataCriacao, 
			Integer tipoMovimentacao) {
		
		var jpql = new StringBuilder();
		jpql.append("from Movimentacao where 0 = 0 ");
		
		if(StringUtils.hasLength(siglaSubscritor))
			jpql.append("and siglaSubscritor = :siglaSubscritor ");
		
		if(dataCriacao != null)
			jpql.append("and dataCriacao	= :dataCriacao ");
		
		if(tipoMovimentacao != null)
			jpql.append("and tipoMovimentacao = :tipoMovimentacao ");
		
		return manager.createQuery(jpql.toString(), Movimentacao.class)
				.setParameter("siglaSubscritor", siglaSubscritor)
				.setParameter("dataCriacao", dataCriacao)
				.setParameter("tipoMovimentacao", tipoMovimentacao)
				.getResultList();
	}

	@Override
	public List<Movimentacao> movimentacoes(String siglaDocumento, Integer tipoMovimentacao) { 
		Documento documento = documentoRepository.findBySigla(siglaDocumento);
		
		if(documento == null)
			throw new NotFoundDocumentoException("Documento com a sigla (" + siglaDocumento + ") não exite.");
		
		Long documentoId = documento.getId();
		
		var jpql = new StringBuilder();
		jpql.append("from Movimentacao m where 0 = 0 ");
		
		if(StringUtils.hasLength(siglaDocumento))
			jpql.append("and documento = :documento ");
		
		if(tipoMovimentacao != null)
			jpql.append("and tipoMovimentacao = :tipoMovimentacao ");
		
		jpql.append("and dataFinalizacao IS NULL ");
		
		return manager.createQuery(jpql.toString(), Movimentacao.class)
				.setParameter("documento", documento)
				.setParameter("tipoMovimentacao", tipoMovimentacao)
				.getResultList();
	}
	
}
