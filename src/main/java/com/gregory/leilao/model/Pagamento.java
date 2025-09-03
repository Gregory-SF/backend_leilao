package com.gregory.leilao.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Pagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	private Double valor;
	private LocalDateTime dataHora = LocalDateTime.now();
	@NotBlank
	private String status; // Da pra trocar pra enum
}
