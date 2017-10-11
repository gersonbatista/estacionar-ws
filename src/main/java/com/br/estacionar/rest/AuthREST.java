package com.br.estacionar.rest;

import java.security.Principal;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.br.estacionar.business.UsuarioBC;
import com.br.estacionar.business.VeiculoBC;
import com.br.estacionar.dto.CadastroDTO;
import com.br.estacionar.entity.Usuario;
import com.br.estacionar.entity.Veiculo;

import br.gov.frameworkdemoiselle.security.Credentials;
import br.gov.frameworkdemoiselle.security.LoggedIn;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.frameworkdemoiselle.util.ValidatePayload;
import io.swagger.annotations.Api;

@Api
@Path("auth")
public class AuthREST extends BasicREST {

	private static final long serialVersionUID = 1L;

	@Inject
	private SecurityContext securityContext;

	@Inject
	private UsuarioBC usuarioBC;

	@Inject
	private VeiculoBC veiculoBC;

	@POST
	@Path("login")
	@ValidatePayload
	@Consumes("application/json")
	@Produces("application/json")
	public Principal login(CredentialsBody body) {
		Credentials credentials = Beans.getReference(Credentials.class);
		credentials.setUsername(body.username);
		credentials.setPassword(body.password);

		securityContext.login();
		return securityContext.getUser();
	}

	@POST
	@LoggedIn
	@Path("logout")
	@ValidatePayload
	public void logout() {
		securityContext.logout();
	}

	@POST
	@Path("novaconta")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response novaConta(CadastroDTO dto) throws Exception {
		try {
			Usuario usuario = new Usuario(dto.getNome(), dto.getEmail(), dto.getTelefone(), null, dto.getExibeTelefone(),
					dto.getExibeNome());
			usuarioBC.salvar(usuario);

			Veiculo veiculo = new Veiculo(dto.getPlaca(), usuario);
			veiculoBC.salvar(veiculo);

			return ResponseBuilder.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBuilder.internalServerError();
		}

	};

	public static class CredentialsBody {

		@NotNull(message = "{required.field}")
		@Size(min = 1, message = "{required.field}")
		public String username;

		@NotNull(message = "{required.field}")
		@Size(min = 1, message = "{required.field}")
		public String password;
	}
}
