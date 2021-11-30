package com.br.mathlehnen.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	@OneToMany(mappedBy = "votacao", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Voto> votos;
		
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
	

	public List<Voto> getVotos() {
		return votos;
	}

	public void setVoto(List<Voto> votos) {
		this.votos = votos;
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

