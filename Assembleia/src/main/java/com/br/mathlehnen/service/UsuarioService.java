package com.br.mathlehnen.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.mathlehnen.exception.CpfException;
import com.br.mathlehnen.exception.CriptografiaException;
import com.br.mathlehnen.exception.EmailException;
import com.br.mathlehnen.exception.UsuarioServiceException;
import com.br.mathlehnen.model.Usuario;
import com.br.mathlehnen.model.Votacao;
import com.br.mathlehnen.repository.UsuarioRepository;
import com.br.mathlehnen.util.SenhaUtil;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository ur;
	
	public Usuario salvarUsuario(Usuario usuario) throws Exception {
		if (ur.findByCpf(usuario.getCpf()) != null) {
			throw new CpfException("Já existe um cpf cadastrado para: " + usuario.getCpf() + ".");
		}
		
		if (ur.findByEmail(usuario.getEmail()) != null) {
			throw new EmailException("Já existe um email cadastrado para: " + usuario.getEmail() + ".");
		}
		try {
			usuario.setSenha(SenhaUtil.md5(usuario.getSenha()));
		} catch (NoSuchAlgorithmException e) {
			throw new CriptografiaException("Erro na criptografia da senha.");
		}
		
		return ur.save(usuario);
	}
	
	public Usuario loginUser (String email, String senha) throws UsuarioServiceException {
		Usuario usuario = ur.buscarLogin(email, senha);
		return usuario;
	}
	
	/*public void verificarSeVotou(Votacao votacao, Usuario usuario) throws UsuarioServiceException {
		if (ur.verificarSeVotou(votacao.getId(), usuario.getId()) != null) {
			throw new UsuarioServiceException("O usuario já votou nesta votação.");
		}
	}*/
}
