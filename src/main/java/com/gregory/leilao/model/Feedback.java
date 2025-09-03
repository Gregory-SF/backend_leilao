package com.gregory.leilao.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "feedback")
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String comentario;
	@NotBlank
	private Integer nota;
	@NotBlank
	private LocalDateTime dataHora = LocalDateTime.now();
	@JoinColumn(name = "pessoa_id", nullable = false)
	@ManyToOne
	private Pessoa pessoa; // Escritor? talvez tenha que tirar
	@OneToOne
	private Pessoa destinatario;
	
}
