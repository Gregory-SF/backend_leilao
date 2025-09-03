package com.gregory.leilao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pessoa_perfil")
public class PessoaPerfil {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@ManyToOne
	@JoinColumn(name = "id_perfil")
	private Perfil perfil;
	
	@ManyToOne
	@JoinColumn(name = "id_pessoa")
	@JsonIgnore
	private Pessoa pessoa;

}
