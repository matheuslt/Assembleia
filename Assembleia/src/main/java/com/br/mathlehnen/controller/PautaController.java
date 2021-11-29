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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.mathlehnen.model.Pauta;
import com.br.mathlehnen.repository.PautaRepository;

@RestController
public class PautaController {
	@Autowired
	private PautaRepository pr;
	
	
	public PautaController(PautaRepository pr) {
		this.pr = pr;
	}
	
	
	@GetMapping("/pautas")
	public List<Pauta> listarPauta()
	{
		return pr.findAll();
	}
	
	@PostMapping("/pautas")
	@ResponseStatus(HttpStatus.CREATED)
	public Pauta criarPauta(@RequestBody @Valid Pauta pauta, BindingResult br) {
		Pauta pautaRetorno = new Pauta();
		if (br.hasErrors()) {
			pautaRetorno = pauta;
		}
		else {
			pautaRetorno = pr.save(pauta);
		}
		return pautaRetorno;
	}
	
	@GetMapping("/pautas/{id}")
	public ResponseEntity<Pauta> getPauta(@PathVariable Long id) {
		return pr.findById(id).map(record -> ResponseEntity.ok().body(record)).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/pautas/{id}")
	public ResponseEntity<?> removerPauta(@PathVariable Long id) {
		return pr.findById(id).map(record -> {pr.deleteById(id); return ResponseEntity.ok().build();}).orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/pautas/{id}")
	public ResponseEntity<Pauta> alterarPauta(@PathVariable Long id, @RequestBody Pauta pauta) {
		return pr.findById(id).map(record -> {record.setDescricao(pauta.getDescricao());
		                                      Pauta pautaAux = pr.save(record);
		                                      return ResponseEntity.ok().body(pautaAux);}).orElse(ResponseEntity.notFound().build());
	}
}
