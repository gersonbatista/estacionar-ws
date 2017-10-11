package com.br.estacionar.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

@ApplicationPath("api")
public class Api extends Application {

	public Api() {
		BeanConfig conf = new BeanConfig();
		conf.setTitle("Estacionar - App de gestão de estacionamento");
		conf.setDescription("Servicos do sistema de gestão de estacionamento");
		conf.setVersion("1.0");
		conf.setHost("localhost:8080");
		conf.setBasePath("estacioanr/api");
		conf.setSchemes(new String[] { "http" });
		conf.setResourcePackage("com.br.estacionar.rest");
		conf.setScan(true);
	}

	@Override
	public Set<Class<?>> getClasses() {

		Set<Class<?>> resources = new HashSet<>();
		resources.add(AuthREST.class);
		resources.add(BookmarkREST.class);
		resources.add(UsuarioREST.class);
		resources.add(CadastroREST.class);
		resources.add(VeiculoREST.class);

		// classes do swagger...
		resources.add(ApiListingResource.class);
		resources.add(SwaggerSerializers.class);

		return resources;
	}

}
