package com.br.mathlehnen.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pauta")
public class Pauta implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pauta_id")
	private Long id;
	@Column(name = "descricao")
	@Size(min = 1, max = 200, message = "A descrição deve conter no máximo 200 caracteres.")
	@NotBlank(message = "A descrição não pode ser em branco.")
	private String descricao;
	@OneToOne
	@JoinColumn(name = "votacao_id")
	private Votacao votacao;
	

	public Pauta(String descricao, Votacao votacao) {
		super();
		this.descricao = descricao;
		this.votacao = votacao;
	}
	
	public Pauta() {
		super();
	}


	public Long getId() {
		return id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Votacao getVotacao() {
		return votacao;
	}

	public void setVotacao(Votacao votacao) {
		this.votacao = votacao;
	}
}