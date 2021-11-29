package com.br.mathlehnen.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.mathlehnen.model.Pauta;
import com.br.mathlehnen.model.Votacao;
import com.br.mathlehnen.repository.PautaRepository;
import com.br.mathlehnen.repository.VotacaoRepository;

@RestController
public class VotacaoController {
	@Autowired
	private PautaRepository pr;
	@Autowired
	private VotacaoRepository vr;
	
	
	public VotacaoController(PautaRepository pr, VotacaoRepository vr) {
		super();
		this.pr = pr;
		this.vr = vr;
	}

	
	@GetMapping("/votacoes")
	public List<Votacao> listaVotacao()
	{
		return vr.findAll();
	}
	
	@GetMapping("/votacoes/id={id}")
	public ResponseEntity<?> getVotacao(@PathVariable Long id) {
		return vr.findById(id).map(record -> ResponseEntity.ok().body(record)).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/votacoes/idPauta={idPauta}")
	@ResponseStatus(HttpStatus.CREATED)
	public Votacao criarVotacao(@PathVariable Long idPauta, @Valid Votacao votacao, BindingResult br) {
		Votacao votacaoRetorno = new Votacao();
		if (br.hasErrors()) {
			votacaoRetorno = votacao;
		}
		else {
			Pauta pauta = new Pauta();
			pauta = pr.findById(idPauta).get();
			pauta.setVotacao(votacao);
			votacaoRetorno = vr.save(votacao);
			pr.save(pauta);
		}
		return votacaoRetorno;
	}
	
	@DeleteMapping("/votacoes/id={id}")
	public ResponseEntity<?> removerVotacao(@PathVariable Long id) {
		return vr.findById(id).map(record -> {vr.deleteById(id); return ResponseEntity.ok().build();}).orElse(ResponseEntity.notFound().build());
		
	}
}
