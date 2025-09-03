package com.gregory.leilao.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class ErrorResponse {
	
	private LocalDateTime dataHora;
	private int codigo;
	private String erro;
	private String mensagem;
	private String caminho;
	private List<String> detalhes;
	
	public ErrorResponse(int codigo, String erro, String mensagem, String caminho, List<String> detalhes) {
		dataHora = LocalDateTime.now();
		this.codigo = codigo;
		this.erro = erro;
		this.mensagem = mensagem;
		this.caminho = caminho;
		this.detalhes = detalhes;
	}
}
