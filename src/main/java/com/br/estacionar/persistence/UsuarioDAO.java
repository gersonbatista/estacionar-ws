package com.br.estacionar.persistence;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.br.estacionar.entity.Usuario;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class UsuarioDAO extends JPACrud<Usuario, Long> {

	private static final long serialVersionUID = 1L;

	public List<Usuario> find(String filter) {
		StringBuffer ql = new StringBuffer();
		ql.append("  from Usuario u ");
		ql.append(" where lower(b.description) like :description ");
		ql.append("    or lower(b.link) like :link ");

		TypedQuery<Usuario> query = getEntityManager().createQuery(ql.toString(), Usuario.class);
		query.setParameter("description", "%" + filter.toLowerCase() + "%");
		query.setParameter("link", "%" + filter.toLowerCase() + "%");

		return query.getResultList();
	}

	public Usuario login(String email, String senha) {
		final String hql = "select u from Usuario u where u.email = :email and u.senha = :senha ";
		try {
			Usuario usuario = getEntityManager().createQuery(hql, Usuario.class).setParameter("email", email)
					.setParameter("senha", senha).getSingleResult();
			return usuario;
		} catch (NoResultException e) {
			return null;
		}
	}
}
