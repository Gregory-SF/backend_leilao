package com.gregory.leilao.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gregory.leilao.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	@Query("from Pessoa where email=:email")
	public Page<Pessoa> buscaEmail(@Param("email") String email, Pageable pageable);
	
	public Optional<Pessoa> findByEmail(String email);

	public String findSenhaByEmail(String email);
}
