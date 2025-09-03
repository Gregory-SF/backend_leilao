package com.gregory.leilao.controller;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gregory.leilao.dto.LoginDTO;
import com.gregory.leilao.dto.PessoaRequestDTO;
import com.gregory.leilao.dto.PessoaResponseDTO;
import com.gregory.leilao.model.Perfil;
import com.gregory.leilao.model.Pessoa;
import com.gregory.leilao.model.PessoaPerfil;
import com.gregory.leilao.service.AutenticationService;
import com.gregory.leilao.service.PerfilService;
import com.gregory.leilao.service.PessoaService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/autenticacao")
public class AuthenticationController {

	@Autowired
	private AutenticationService service;
	
	@Autowired
	private PessoaService pessoaService;
		
	@PostMapping("/login")
	public ResponseEntity<PessoaResponseDTO> login(@Valid @RequestBody LoginDTO pessoa) {
		return ResponseEntity.ok(service.autenticar(pessoa));
	}
	
	@PostMapping("/cadastro")
	public ResponseEntity<Void> registrar(@RequestBody PessoaRequestDTO pessoadto) {
		Pessoa pessoa = pessoaService.inserirPerfis(pessoadto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(pessoa.getId()).toUri();
		 //		PessoaResponseDTO reponse = new PessoaResponseDTO(pessoa.getNome(), pessoa.getEmail(), pessoa.getPessoaPerfil().getFirst());;
		return ResponseEntity.created(location).build();
	}	
}
