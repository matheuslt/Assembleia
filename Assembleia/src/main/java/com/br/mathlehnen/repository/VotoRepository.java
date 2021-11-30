package com.br.mathlehnen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.mathlehnen.model.Voto;
@Repository
public interface VotoRepository extends JpaRepository<Voto, Long>{

	@Query("select i from Voto i where i.votacao.id = :idVotacao and i.usuario.id = :idUsuario")
	public Voto verificarSeVotou(Long idVotacao, Long idUsuario);
}
