package com.br.mathlehnen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.mathlehnen.model.Voto;
@Repository
public interface VotoRepository extends JpaRepository<Voto, Long>{

}
