package com.br.estacionar.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = SEQUENCE)
	private Long id;

	@Column(name = "NOME", nullable = false)
	private String nome;

	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Column(name = "TELEFONE", nullable = false)
	private String telefone;

	@Column(name = "SENHA")
	private String senha;

	@Column(name = "EXIBE_NOME")
	private Boolean exibeNome;

	@Column(name = "EXIBE_TELEFONE")
	private Boolean exibeTelefone;

	@Column(name = "SENHA_VERIFICACAO")
	private String senhaVerificacao;

	public Usuario() {
	}

	public Usuario(String nome, String email, String telefone, String senha, Boolean exibeTelefone, Boolean exibeNome) {
		super();
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.senha = senha;
		this.exibeTelefone = exibeTelefone;
		this.exibeNome = exibeNome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	// public Boolean getExibeNome() {
	// return exibeNome;
	// }
	//
	// public void setExibeNome(Boolean exibeNome) {
	// this.exibeNome = exibeNome;
	// }

	public Boolean getExibeTelefone() {
		return exibeTelefone;
	}

	public void setExibeTelefone(Boolean exibeTelefone) {
		this.exibeTelefone = exibeTelefone;
	}

	public String getSenhaVerificacao() {
		return senhaVerificacao;
	}

	public void setSenhaVerificacao(String senhaVerificacao) {
		this.senhaVerificacao = senhaVerificacao;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
