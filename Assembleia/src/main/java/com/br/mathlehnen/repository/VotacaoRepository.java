package com.br.mathlehnen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.mathlehnen.model.Votacao;

@Repository
public interface VotacaoRepository extends JpaRepository<Votacao, Long>{
	
}
