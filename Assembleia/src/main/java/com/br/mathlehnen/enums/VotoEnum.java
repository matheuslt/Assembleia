package com.br.mathlehnen.enums;

public enum VotoEnum {
	Não("NÃO"),
	Sim("SIM");

	private String voto;
	
	private VotoEnum(String voto) {
		this.voto = voto;
	}
}
