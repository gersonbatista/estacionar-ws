package com.br.estacionar.rest;

import java.io.Serializable;
import java.net.URI;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.br.estacionar.dto.MensagemDTO;

import br.gov.frameworkdemoiselle.pagination.Pagination;
import br.gov.frameworkdemoiselle.pagination.PaginationContext;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

public class BasicREST implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private SecurityContext securityContext;

	@Inject
	private PaginationContext paginationContext;

	@Inject
	private ResourceBundle bundle;

	public static ResponseBuilder ResponseBuilder = new ResponseBuilder();

	public SecurityContext getSecurityContext() {
		return this.securityContext;
	}

	public boolean isLoggedIn() {
		return this.securityContext.isLoggedIn();
	}

	public <T> Pagination getPaginationContext(final Class<T> classe) {
		return this.paginationContext.getPagination(classe, true);
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public Response ok() {
		return Response.ok().build();
	}

	public Response ok(String msg) {
		String mensagem = bundle.containsKey(msg) ? bundle.getString(msg) : msg;
		return Response.ok().entity(new MensagemDTO(mensagem)).build();
	}

	public Response ok(Object retorno) {
		return Response.ok().entity(retorno).build();
	}

	public Response notFound() {
		return Response.status(Status.NOT_FOUND).build();
	}

	public Response created(URI location, String msg) {
		String mensagem = bundle.containsKey(msg) ? bundle.getString(msg) : msg;
		return Response.created(location).entity(new MensagemDTO(mensagem)).build();
	}

	public static final class ResponseBuilder {

		private ResponseBuilder() {
		};

		private java.util.ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());

		private String getMessage(String msg) {
			if (msg == null) {
				throw new IllegalArgumentException("Parâmetro 'msg' deve ser informado.");
			}
			return bundle.containsKey(msg) ? bundle.getString(msg) : msg;
		}

		public Response ok() {
			return Response.ok().build();
		}

		public Response ok(String msg) {
			return Response.ok().entity(new MensagemDTO(getMessage(this.getMessage(msg)))).build();
		}

		public <T> Response ok(T retorno) {
			if (retorno != null) {
				return Response.ok().entity(retorno).build();
			} else {
				return notFound();
			}
		}

		public <T> Response ok(List<T> retorno) {
			if (retorno != null && !retorno.isEmpty()) {
				return Response.ok().entity(retorno).build();
			} else {
				return notFound();
			}
		}

		public Response notFound() {
			return Response.status(Status.NOT_FOUND).entity(new MensagemDTO(this.getMessage("registro.nao.encontrado"))).build();
		}

		public Response notFound(String msg) {
			return Response.status(Status.NOT_FOUND).entity(new MensagemDTO(this.getMessage(msg))).build();
		}

		public Response created(URI location, String msg) {
			return Response.created(location).entity(new MensagemDTO(this.getMessage(msg))).build();
		}

		public Response created(String msg) {
			return Response.status(Status.CREATED).entity(new MensagemDTO(this.getMessage(msg))).build();
		}

		public <T> Response created(T retorno) {
			if (retorno != null) {
				return Response.status(Status.CREATED).entity(retorno).build();
			} else {
				return notFound();
			}
		}

		public Response noContent(String msg) {
			return Response.status(Status.NO_CONTENT).entity(new MensagemDTO(this.getMessage(msg))).build();
		}

		/**
		 * Método usado para indicar uma falha desconhecida do servidor.
		 * 
		 * @return objeto que contém informações sobre a falha no servidor
		 */
		public Response internalServerError() {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(new MensagemDTO(this.getMessage("servidor.erro.naoidentificado"))).build();
		}

		/**
		 * Método usado para indicar uma falha desconhecida do servidor.
		 * 
		 * @return objeto que contém informações sobre a falha no servidor
		 */
		public <T> Response internalServerError(T object) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(object).build();
		}

		/**
		 * Método usado para indicar uma falha desconhecida do servidor.
		 * 
		 * @param msg
		 *            mensagem a ser apresentada para o cliente
		 * @return objeto que contém informações sobre a falha no servidor
		 */
		public Response internalServerError(String msg) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new MensagemDTO(this.getMessage(msg))).build();
		}

		/**
		 * Método usado para indicar quando algum parâmetro não foi informado corretamente
		 * 
		 * @param msg
		 * @return
		 */
		public Response preConditionFailed(String msg) {
			return Response.status(Status.PRECONDITION_FAILED).entity(new MensagemDTO(this.getMessage(msg))).build();
		}

		/**
		 * Método usado para indicar que o usuário não tem autorização para acessar um recurso.
		 * 
		 * @param msg
		 *            mensagem a ser apresentada para o cliente
		 * @return objeto que contém informações sobre falta de privilégios para acessar um recurso
		 */
		public Response forbidden(String msg) {
			return Response.status(Status.FORBIDDEN).entity(new MensagemDTO(this.getMessage(msg))).build();
		}

		/**
		 * Método usado para indicar que o usuário não tem autorização para acessar um recurso.
		 * 
		 * @param msg
		 *            mensagem a ser apresentada para o cliente
		 * @return objeto que contém informações sobre falta de privilégios para acessar um recurso
		 */
		public Response forbidden(MensagemDTO mensagemDTO) {
			return Response.status(Status.FORBIDDEN).entity(mensagemDTO).build();
		}
	}
}
