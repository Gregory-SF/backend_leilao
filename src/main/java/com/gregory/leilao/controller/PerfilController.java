package com.gregory.leilao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gregory.leilao.model.Perfil;
import com.gregory.leilao.service.PerfilService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/perfil")
@Slf4j
public class PerfilController {

	@Autowired
	private PerfilService perfilService;
	
	@GetMapping
	public ResponseEntity<Page<Perfil>> buscartodos(Pageable pageable){
		return ResponseEntity.ok(perfilService.buscarTodos(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Perfil> buscarPorId(@PathVariable Long id){
		return ResponseEntity.ok(perfilService.buscarPorId(id));
	}
	
	@PostMapping
	public ResponseEntity<Perfil> inserir(@Valid @RequestBody Perfil perfil){
		log.info(perfil.toString());
		return ResponseEntity.ok(perfilService.inserir(perfil));
	}

	@PutMapping
	public ResponseEntity<Perfil> alterar(@Valid @RequestBody Perfil perfil){
		return ResponseEntity.ok(perfilService.alterar(perfil));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> excluir(@PathVariable("id") Long id) {
		perfilService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
