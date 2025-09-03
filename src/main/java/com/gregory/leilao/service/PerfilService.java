package com.gregory.leilao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gregory.leilao.exception.NotFoundException;
import com.gregory.leilao.model.Perfil;
import com.gregory.leilao.repository.PerfilRepository;

@Service
public class PerfilService {
	
	@Autowired
	PerfilRepository repository;
	
	@Autowired
	MessageSource messageSource;
	
	public Perfil inserir(Perfil perfil) {
		return repository.save(perfil);
	}
	
	public Perfil alterar(Perfil perfil) {
		Perfil perfilBanco = buscarPorId(perfil.getId());
		perfilBanco.setNome(perfil.getNome());
		return repository.save(perfilBanco);
	}

	public void deletar(Long id) {
		Perfil perfilBanco = buscarPorId(id);
		repository.delete(perfilBanco);
	}
	
	public Perfil buscarPorId(Long id) {
		Perfil perfilBanco = repository.findById(id)
				.orElseThrow(() -> new NotFoundException
				(messageSource.getMessage("perfil.notfound",
						new Object[] { id }, LocaleContextHolder.getLocale())));
		return repository.save(perfilBanco);
	}
	
	public Page<Perfil> buscarTodos(Pageable pageable){
		return repository.findAll(pageable);
	}
	
}
