package com.br.mathlehnen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.mathlehnen.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	@Query("select i from Usuario i where i.cpf = :cpf")
	public Usuario findByCpf(String cpf);
	
	@Query("select i from Usuario i where i.email = :email")
	public Usuario findByEmail(String email);
	
	@Query("select i from Usuario i where i.email = :email and i.senha = :senha")
	public Usuario buscarLogin(String email, String senha);
}
