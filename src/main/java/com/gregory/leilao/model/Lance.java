package com.gregory.leilao.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "lance")
public class Lance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private Double valorLance;
	@NotBlank
	private LocalDateTime dataHora = LocalDateTime.now();

}
