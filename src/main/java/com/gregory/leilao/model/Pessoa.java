package com.gregory.leilao.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
@Table(name = "pessoa")
public class Pessoa implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "{validation.name.notblank}")
	private String nome;
	@NotBlank(message = "{validation.email.notblank}")
	@Email(message = "{validation.email.notvalid}")
	private String email;
	private String senha;
	
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Setter(value = AccessLevel.NONE)
	private List<PessoaPerfil> pessoaPerfil;
	private String codigoValidacao;
	private Date validadeCodigoValidacao;
	private Boolean ativo;
	
	
	public void setPessoaPerfil(List<PessoaPerfil> pessoaPerfil) {
		for(PessoaPerfil p : pessoaPerfil) {
			p.setPessoa(this);
		}
		this.pessoaPerfil = pessoaPerfil;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return pessoaPerfil.stream().map(user -> 
			new SimpleGrantedAuthority(user.getPerfil().getNome()))
				.collect(Collectors.toList());
	}
	@Override
	public String getPassword() {
		return this.senha;
	}
	@Override
	public String getUsername() {
		return this.email;
	}
	
}
