package com.gregory.leilao.controller;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gregory.leilao.dto.CategoriaCreateDto;
import com.gregory.leilao.model.Categoria;
import com.gregory.leilao.service.CategoriaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaService service;
	
	@PostMapping
	public ResponseEntity<Void> criar(@RequestBody CategoriaCreateDto dto, WebRequest request){
		log.info("Categoria {}", dto);
		
		Categoria catSaved = service.inserir(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(catSaved.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping
	public ResponseEntity<List<Categoria>> buscartodos(){
		List<Categoria> categorias = service.buscarTodos();
		return ResponseEntity.ok(categorias);
	}
	
	@GetMapping("/paged")
	public ResponseEntity<Page<Categoria>> buscartodos(Pageable pageable){
		Page<Categoria> categorias = service.buscarTodosPaged(pageable);
		return ResponseEntity.ok(categorias);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id){
		return ResponseEntity.ok(service.buscarPorId(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> alterar(@PathVariable Long id, @RequestBody Categoria cat) {
		return ResponseEntity.ok(service.alterar(id, cat));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id){
		service.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
