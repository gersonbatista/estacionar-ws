package com.br.estacionar.dto;

import java.io.Serializable;

public class CadastroDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	public String placa;
	public String telefone;
	public String nome;
	public String email;
	public Boolean exibeNome;
	public Boolean exibeTelefone;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getExibeNome() {
		return exibeNome;
	}

	public void setExibeNome(Boolean exibeNome) {
		this.exibeNome = exibeNome;
	}

	public Boolean getExibeTelefone() {
		return exibeTelefone;
	}

	public void setExibeTelefone(Boolean exibeTelefone) {
		this.exibeTelefone = exibeTelefone;
	}

}
