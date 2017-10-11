package com.br.estacionar.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.br.estacionar.business.UsuarioBC;
import com.br.estacionar.entity.Usuario;

import br.gov.frameworkdemoiselle.BadRequestException;
import br.gov.frameworkdemoiselle.NotFoundException;
import br.gov.frameworkdemoiselle.security.LoggedIn;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.ResourceBundle;
import br.gov.frameworkdemoiselle.util.Strings;
import br.gov.frameworkdemoiselle.util.ValidatePayload;
import io.swagger.annotations.Api;

@Api
@LoggedIn
@Path("usuario")
public class UsuarioREST extends BasicREST {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioBC bc;

	@Inject
	private ResourceBundle bundle;

	@GET
	@Produces("application/json")
	public List<Usuario> find(@QueryParam("q") String query) throws Exception {
		List<Usuario> result;

		if (Strings.isEmpty(query)) {
			result = bc.findAll();
		} else {
			result = bc.find(query);
		}

		return result;
	}

	@GET
	@Path("{id}")
	@Produces("application/json")
	public Usuario load(@PathParam("id") Long id) throws Exception {
		Usuario result = bc.load(id);

		if (result == null) {
			throw new NotFoundException();
		}

		return result;
	}

	@POST
	@LoggedIn
	@Transactional
	@ValidatePayload
	@Produces("application/json")
	@Consumes("application/json")
	public Response insert(Usuario body) throws Exception {
		try {
			checkId(body);
			bc.insert(body).getId().toString();
			return ResponseBuilder.created(bundle.getString("Usuário salvo com sucesso"));
		} catch (Exception e) {
			return ResponseBuilder.internalServerError();
		}
	}

	@PUT
	@LoggedIn
	@Path("{id}")
	@Transactional
	@ValidatePayload
	@Produces("application/json")
	@Consumes("application/json")
	public void update(@PathParam("id") Long id, Usuario body) throws Exception {
		checkId(body);
		load(id);

		body.setId(id);
		bc.update(body);
	}

	@DELETE
	@LoggedIn
	@Path("{id}")
	@Transactional
	public void delete(@PathParam("id") Long id) throws Exception {
		load(id);
		bc.delete(id);
	}

	@DELETE
	@LoggedIn
	@Transactional
	public void delete(List<Long> ids) throws Exception {
		bc.delete(ids);
	}

	private void checkId(Usuario entity) throws Exception {
		if (entity.getId() != null) {
			throw new BadRequestException();
		}
	}

	// @GET
	// @Path("{codigo}")
	// @Produces("application/json")
	// public load(@PathParam("id") Long id) throws Exception {
	// Usuario result = bc.load(id);
	//
	// if (result == null) {
	// throw new NotFoundException();
	// }
	//
	// return result;
	// }
}
