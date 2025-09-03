package com.gregory.leilao.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "imagem")
public class Imagem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	private LocalDateTime dataHoraCadastro = LocalDateTime.now();
	@NotBlank
	private String nomeImagem;
	@JoinColumn(name = "leilao_id")
	@ManyToOne
	private Leilao leilao;
}
