package com.br.mathlehnen.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usuario_id")
	private Long id;
	@Column(name = "nome")
	@Size(min = 1, max = 200, message = "O nome deve conter no máximo 200 caracteres.")
	@NotBlank(message = "O nome não pode ser em branco.")
	private String nome;
	@Column(name = "cpf")
	@Size(min = 11, max = 11, message = "O CPF deve conter 11 caracteres.")
	@NotBlank(message = "O CPF não pode ser em branco.")
	private String cpf;
	@Column(name = "email")
	@NotBlank(message = "O email não pode ser em branco.")
	@Email
	private String email;
	@Column(name = "senha")
	@NotBlank(message = "O senha não pode ser em branco.")
	private String senha;
	@ManyToOne
	@JoinColumn(name = "votacao_id")
	private Votacao votacao;
	@OneToOne()
	@JoinColumn(name = "voto_id")
	private Voto voto;
	
	
	public Usuario(String nome, String cpf, Votacao votacao) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.votacao = votacao;
	}
	
	public Usuario() {
		super();
	}


	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	

	public Voto getVoto() {
		return voto;
	}

	public void setVoto(Voto voto) {
		this.voto = voto;
	}

	public Votacao getVotacao() {
		return votacao;
	}

	public void setVotacao(Votacao votacao) {
		this.votacao = votacao;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}
	
	
}

