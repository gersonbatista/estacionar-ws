package com.br.estacionar.business;

import java.util.List;

import com.br.estacionar.entity.Usuario;
import com.br.estacionar.persistence.UsuarioDAO;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@BusinessController
public class UsuarioBC extends DelegateCrud<Usuario, Long, UsuarioDAO> {

	private static final long serialVersionUID = 1L;

	@Startup
	@Transactional
	public void load() {
		if (findAll().isEmpty()) {
			insert(new Usuario("Gerson Batista", "batista.gerson@gmail.com", "", "estacionar", false, false));
			insert(new Usuario("Fulano de Tal", "fulano.tal@gmail.com", "", "estacionar", false, false));
		}
	}

	public List<Usuario> find(String filter) {
		return getDelegate().find(filter);
	}

	public Usuario login(String email, String senha) {
		return getDelegate().login(email, senha);
	}

	@Transactional
	public Long salvar(Usuario usuario) {
		Long id = null;

		if (usuario.getId() == null) {
			id = getDelegate().insert(usuario).getId();
		} else {
			id = getDelegate().update(usuario).getId();
		}

		return id;
	}
}
