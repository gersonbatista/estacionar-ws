package com.br.estacionar.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.br.estacionar.business.VeiculoBC;
import com.br.estacionar.entity.Veiculo;

import br.gov.frameworkdemoiselle.util.ResourceBundle;
import io.swagger.annotations.Api;

@Api
@Path("veiculo")
public class VeiculoREST extends BasicREST {

	private static final long serialVersionUID = 1L;

	@Inject
	private VeiculoBC bc;

	@Inject
	private ResourceBundle bundle;

	@GET
	@Produces("application/json")
	@Path("/pesquisar")
	public List<Veiculo> find(@QueryParam("q") String query) throws Exception {
		List<Veiculo> result = bc.find(query);
		return result;
	}

}
