package com.gregory.leilao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gregory.leilao.model.Calculadora;
import com.gregory.leilao.service.HelloService;

@RestController
public class Hello {

	@Autowired
	HelloService helloService;
	
	@GetMapping("/")
	public String ola() {
		return "Olá spring";
	}
	
//	@PostMapping("/somar")
//	public Calculadora somar(@RequestBody Calculadora calculadora) {
//		return helloService.somar(calculadora);
//	}
}
