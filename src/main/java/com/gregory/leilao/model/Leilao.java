package com.gregory.leilao.model;

import java.time.LocalDateTime;
import java.util.List;

import com.gregory.leilao.enums.StatusLeilao;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "leilao")
public class Leilao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String titulo;
	private String descricao;
	private String descricaoDetalhada;
	private LocalDateTime dataHoraInicio;
	private LocalDateTime dataHoraFim;
	@Enumerated(EnumType.STRING)
	private StatusLeilao status;
	private String observacao;
	private Double valorIncremento;
	private Double lanceMinimo;

	@OneToMany
	private List<Lance> lances;
	
	//@Transient // Verificar
	@OneToMany(mappedBy = "leilao")
	private List<Imagem> imagens;

	@OneToOne(optional = true)
	private Pagamento pagamento;
	
	@OneToOne
	private Categoria categoria;
	

}
