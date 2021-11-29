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
import javax.persistence.OneToOne;
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
	@OneToOne(mappedBy = "voto", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Usuario usuario;
	
	
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
}
