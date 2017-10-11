package com.br.estacionar.dto;

import java.io.Serializable;

public class MensagemDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String titulo;
	private String mensagem;

	public MensagemDTO(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public MensagemDTO(String titulo, String mensagem) {
		this.titulo = titulo;
		this.mensagem = mensagem;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}