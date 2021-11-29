package com.br.mathlehnen.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpSession;
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

import com.br.mathlehnen.exception.UsuarioServiceException;
import com.br.mathlehnen.model.Usuario;
import com.br.mathlehnen.model.Votacao;
import com.br.mathlehnen.repository.UsuarioRepository;
import com.br.mathlehnen.repository.VotacaoRepository;
import com.br.mathlehnen.service.UsuarioService;
import com.br.mathlehnen.util.SenhaUtil;

@RestController
public class UsuarioController {
	@Autowired
	private VotacaoRepository vr;
	@Autowired
	private UsuarioRepository ur;
	
	@Autowired
	private UsuarioService us;
	
	
	public UsuarioController(VotacaoRepository vr, UsuarioRepository ur) {
		super();
		this.vr = vr;
		this.ur = ur;
	}

	
	@GetMapping("/usuarios")
	public List<Usuario> listaUsuario()
	{
		return ur.findAll();
	}
	
	@PostMapping("/usuarios")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario cadastrarUsuario(@RequestBody Usuario usuario) {
		return ur.save(usuario);
	}
	

	@GetMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> getUsuario(@PathVariable Long id) {
		return ur.findById(id).map(record -> ResponseEntity.ok().body(record)).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/votacoes/{idVotacao}/usuarios")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario criarUsuario(@PathVariable Long idVotacao, @RequestBody @Valid Usuario usuario, BindingResult br) throws Exception {
		Usuario usuarioRetorno = new Usuario();
		if (br.hasErrors()) {
			usuarioRetorno = usuario;
		}
		else {
			Votacao votacao = new Votacao();
			votacao = vr.findById(idVotacao).get();
			votacao.getUsuarios().add(usuario);
			usuario.setVotacao(votacao);
			usuarioRetorno = us.salvarUsuario(usuario);
		}
		return usuarioRetorno;
	}

	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<?> removerUsuario(@PathVariable Long id) {
		return vr.findById(id).map(record -> {vr.deleteById(id); return ResponseEntity.ok().build();}).orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> alterarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
		return ur.findById(id).map(record -> {record.setCpf(usuario.getCpf());
		                                      record.setNome(usuario.getNome());
		                                      Usuario usuarioAux = ur.save(record);
		                                      return ResponseEntity.ok().body(usuarioAux);}).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/usuarios/login")
	public Usuario login(@Valid Usuario usuario, BindingResult br, HttpSession sessao) throws NoSuchAlgorithmException, UsuarioServiceException {
		Usuario usuarioRetorno = new Usuario();
		if (br.hasErrors()) {
			usuarioRetorno = usuario;
		} 
		else {
			usuarioRetorno = us.loginUser(usuario.getEmail(), SenhaUtil.md5(usuario.getSenha()));
			if (usuarioRetorno != null) {
				sessao.setAttribute("UsuarioLogado", usuarioRetorno);
			}
				
		}
		return usuarioRetorno;
	}
	
	@PostMapping("/usuarios/logout")
	public void logout(HttpSession sessao) {
		sessao.invalidate();
	}
}
