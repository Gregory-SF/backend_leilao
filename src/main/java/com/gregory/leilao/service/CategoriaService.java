package com.gregory.leilao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gregory.leilao.dto.CategoriaCreateDto;
import com.gregory.leilao.exception.NotFoundException;
import com.gregory.leilao.model.Categoria;
import com.gregory.leilao.model.Perfil;
import com.gregory.leilao.model.Pessoa;
import com.gregory.leilao.repository.CategoriaRepository;
import com.gregory.leilao.repository.PessoaRepository;
import com.gregory.leilao.security.AuthPessoaProvider;

import jakarta.transaction.Transactional;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private AuthPessoaProvider provider;
	
	@Transactional
	public Categoria inserir(CategoriaCreateDto dto) {
		Categoria cat = new Categoria();
		Pessoa pessoa = provider.getUsuarioAutenticado();
		cat.setNome(dto.getNome());
		cat.setObservacao(dto.getObservacao());
		cat.setPessoa(pessoa);
		return repository.save(cat);
	}
	
	public Categoria buscarPorId(Long id) {
		return repository.findById(id).orElseThrow(
				() -> new NotFoundException(String.format("Categoria com id: %d não econtrado!", id))
			);
	}
	
	@Transactional
	public Categoria alterar(Long id, Categoria cat) {
		Categoria catBanco = buscarPorId(id);
		catBanco.setNome(cat.getNome());
		catBanco.setObservacao(cat.getObservacao());
		return repository.save(catBanco);
	}
	
	
	@Transactional
	public void excluir(Long id) {
		repository.delete(buscarPorId(id));
	}
	
	@Transactional
	public List<Categoria> buscarTodos() {
		return repository.findAll();
	}
	
	public Page<Categoria> buscarTodosPaged(Pageable pageable){
		return repository.findAll(pageable);
	}
	

}
