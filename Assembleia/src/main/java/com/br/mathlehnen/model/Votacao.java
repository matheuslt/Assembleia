package com.br.mathlehnen.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.br.mathlehnen.enums.VotoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "votacao")
public class Votacao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "votacao_id")
	private Long id;
	@Column(name = "qtd_votos_sim", nullable = false)
	private int qtdVotosSim;
	@Column(name = "qtd_votos_nao", nullable = false)
	private int qtdVotosNao;
	@OneToOne(mappedBy = "votacao", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Pauta pauta;
	//@OneToMany(mappedBy = "votacao", cascade = CascadeType.ALL, orphanRemoval = true)
	//private List<Usuario> usuarios = new ArrayList<>();
	@OneToMany(mappedBy = "votacao", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Voto> votos = new ArrayList<>();
	
	
	public Votacao(Pauta pauta, /*List<Usuario> usuarios*/ List<Voto> votos) {
		super();
		this.pauta = pauta;
		//this.usuarios = usuarios;
		this.votos = votos;
		setQtdsParaZero();
	}

	public Votacao(Pauta pauta) {
		super();
		this.pauta = pauta;
		setQtdsParaZero();
	}

	public Votacao() {
		super();
		setQtdsParaZero();
	}

	private void setQtdsParaZero() {
		this.qtdVotosNao = 0;
		this.qtdVotosSim = 0;
	}
	
	
	public int getQtdVotosSim() {
		return qtdVotosSim;
	}
	
	public int getQtdVotosNao() {
		return qtdVotosNao;
	}
	
	/*public List<Usuario> getUsuarios() {
		return usuarios;
	}*/
	
	
	public List<Voto> getVotos() {
		return votos;
	}
	
	public Long getId() {
		return id;
	}

	public void contabilizarVotoDoUsuario (Votacao votacao, /*Usuario usuario,*/ Voto voto)
	{
		votacao.votos.add(voto);
		if (VotoEnum.Sim.equals(voto.getVoto())) {
			votacao.qtdVotosSim++;
		}
		else if (VotoEnum.Não.equals(voto.getVoto())) {
			votacao.qtdVotosNao++;
		}
	}
}
