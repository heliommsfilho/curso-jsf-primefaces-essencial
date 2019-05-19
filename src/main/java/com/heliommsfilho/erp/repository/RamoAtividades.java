package com.heliommsfilho.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.heliommsfilho.erp.model.RamoAtividade;

public class RamoAtividades implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	public RamoAtividades() {

	}

	public RamoAtividades(EntityManager manager) {
		this.manager = manager;
	}
	
	public List<RamoAtividade> pesquisar(String descricao) {
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<RamoAtividade> c = cb.createQuery(RamoAtividade.class);
		Root<RamoAtividade> root = c.from(RamoAtividade.class);
		
		c.select(root);
		c.where(cb.like(root.get("descricao"), descricao + "%"));
		
		TypedQuery<RamoAtividade> query = manager.createQuery(c);
		
		return query.getResultList();
	}
}
