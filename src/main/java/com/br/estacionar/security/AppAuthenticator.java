package com.br.estacionar.security;

import java.security.Principal;

import javax.inject.Inject;

import com.br.estacionar.business.UsuarioBC;
import com.br.estacionar.entity.Usuario;

import br.gov.frameworkdemoiselle.security.Credentials;
import br.gov.frameworkdemoiselle.security.InvalidCredentialsException;
import br.gov.frameworkdemoiselle.security.TokenAuthenticator;
import br.gov.frameworkdemoiselle.util.Beans;

public class AppAuthenticator extends TokenAuthenticator {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioBC usuarioBC;

	@Override
	protected Principal customAuthentication() throws Exception {
		Principal user = null;
		final Credentials credentials = Beans.getReference(Credentials.class);

		final Usuario usuario = usuarioBC.login(credentials.getUsername(), credentials.getPassword());

		if (usuario != null) {
			user = new Principal() {

				@SuppressWarnings("unused")
				public String getEmail() {
					return usuario.getEmail();
				}

				@SuppressWarnings("unused")
				public String getNome() {
					return usuario.getNome();
				}

				@SuppressWarnings("unused")
				public Long getId() {
					return usuario.getId();
				}

				@Override
				public String getName() {
					return null;
				}
			};

		} else {
			throw new InvalidCredentialsException();
		}

		return user;
	}
}
