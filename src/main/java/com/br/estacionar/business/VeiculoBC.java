package com.br.estacionar.business;

import java.util.List;

import com.br.estacionar.entity.Veiculo;
import com.br.estacionar.persistence.VeiculoDAO;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@BusinessController
public class VeiculoBC extends DelegateCrud<Veiculo, Long, VeiculoDAO> {

	private static final long serialVersionUID = 1L;

	@Transactional
	public Long salvar(Veiculo veiculo) {
		Long id = null;

		if (veiculo.getId() == null) {
			id = getDelegate().insert(veiculo).getId();
		} else {
			id = getDelegate().update(veiculo).getId();
		}

		return id;
	}

	public List<Veiculo> find(String query) {
		return getDelegate().find(query);
	}
}
