package com.br.mathlehnen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.mathlehnen.exception.UsuarioServiceException;
import com.br.mathlehnen.model.Usuario;
import com.br.mathlehnen.model.Votacao;
import com.br.mathlehnen.repository.VotoRepository;

@Service
public class VotoService {
	@Autowired
	private VotoRepository vr;
	
	public void verificarSeVotou(Votacao votacao, Usuario usuario) throws UsuarioServiceException {
		if (vr.verificarSeVotou(votacao.getId(), usuario.getId()) != null) {
			throw new UsuarioServiceException("O usuario já votou nesta votação.");
		}
	}
}
