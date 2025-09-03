package com.gregory.leilao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gregory.leilao.model.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

	@Query("from Perfil where nome=:nome")
	public Page<Perfil> buscaNome(@Param("nome") String nome, Pageable pageable);

	
}
