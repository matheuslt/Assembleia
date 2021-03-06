package com.br.mathlehnen.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.mathlehnen.exception.UsuarioServiceException;
import com.br.mathlehnen.model.Usuario;
import com.br.mathlehnen.model.Votacao;
import com.br.mathlehnen.model.Voto;
import com.br.mathlehnen.repository.UsuarioRepository;
import com.br.mathlehnen.repository.VotacaoRepository;
import com.br.mathlehnen.repository.VotoRepository;
import com.br.mathlehnen.service.VotoService;

@RestController
public class VotoController {
	@Autowired
	private VotacaoRepository vtr;
	@Autowired
	private UsuarioRepository ur;
	@Autowired
	private VotoRepository vr;

	@Autowired
	private VotoService vs;
	
	public VotoController(VotacaoRepository vtr, UsuarioRepository ur, VotoRepository vr) {
		super();
		this.vtr = vtr;
		this.ur = ur;
		this.vr = vr;
	}
	

	@GetMapping("/votos")
	public List<Voto> listaVotacao()
	{
		return vr.findAll();
	}	
	
	@GetMapping("/votos/id={id}")
	public ResponseEntity<Voto> getVoto(@PathVariable Long id) {
		return vr.findById(id).map(record -> ResponseEntity.ok().body(record)).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/votos/idVotacao={idVotacao}&idUsuario={idUsuario}")
	@ResponseStatus(HttpStatus.CREATED)
	public Voto criarVoto(@PathVariable Long idVotacao, @PathVariable Long idUsuario, @RequestBody @Valid Voto voto, BindingResult br) throws UsuarioServiceException {
		Voto votoRetorno = new Voto();
		if (br.hasErrors()) {
			votoRetorno = voto;
		}
		else {			
			Votacao votacao = new Votacao();
			Usuario usuario = new Usuario();
			votacao = vtr.findById(idVotacao).get();
			usuario = ur.findById(idUsuario).get();
			vs.verificarSeVotou(votacao, usuario);
			voto.setVotacao(votacao);
			voto.setUsuario(usuario);
			votoRetorno = vr.save(voto);
			votacao.contabilizarVotoDoUsuario(votacao, voto);
			vtr.save(votacao);
		}
		return votoRetorno;
	}
}
