package com.br.estacionar.persistence;

import java.util.List;

import javax.persistence.TypedQuery;

import com.br.estacionar.entity.Veiculo;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class VeiculoDAO extends JPACrud<Veiculo, Long> {

	private static final long serialVersionUID = 1L;

	public List<Veiculo> find(String filter) {
		StringBuffer ql = new StringBuffer();
		ql.append("  from Veiculo this ");
		ql.append(" where lower(this.placa) lower(:placa) ");

		TypedQuery<Veiculo> query = getEntityManager().createQuery(ql.toString(), Veiculo.class);
		query.setParameter("placa", filter.toLowerCase());

		return query.getResultList();
	}

}
