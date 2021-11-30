package com.br.mathlehnen.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.br.mathlehnen.enums.VotoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "voto")
public class Voto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "voto_id")
	private Long id;
	@Column(name = "voto")
	@Enumerated(EnumType.STRING)
	private VotoEnum voto;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id")
	@JsonIgnore
	private Usuario usuario;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "votacao_id")
	@JsonIgnore
	private Votacao votacao;
	
	
	public Voto(VotoEnum voto) {
		super();
		this.voto = voto;
	}

	public Voto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public VotoEnum getVoto() {
		return voto;
	}

	public void setVoto(VotoEnum voto) {
		this.voto = voto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Votacao getVotacao() {
		return votacao;
	}

	public void setVotacao(Votacao votacao) {
		this.votacao = votacao;
	}
	
	
}
