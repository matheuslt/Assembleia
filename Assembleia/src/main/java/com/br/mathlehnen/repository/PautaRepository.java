package com.br.mathlehnen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.mathlehnen.model.Pauta;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long>{
	
}
