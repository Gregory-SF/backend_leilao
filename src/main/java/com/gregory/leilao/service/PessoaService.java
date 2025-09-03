package com.gregory.leilao.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.gregory.leilao.dto.PessoaRequestDTO;
import com.gregory.leilao.exception.NotFoundException;
import com.gregory.leilao.model.Perfil;
import com.gregory.leilao.model.Pessoa;
import com.gregory.leilao.model.PessoaPerfil;
import com.gregory.leilao.repository.PessoaRepository;

@Service
public class PessoaService implements UserDetailsService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private EmailService emailService;
		
	public Pessoa inserir(Pessoa pessoa) {
		Pessoa pessoaCadastrada = pessoaRepository.save(pessoa);
//		emailService.enviarEmailSimples(pessoaCadastrada.getEmail(),
//				"Cadastrado com Sucesso",
//				"Cadastro no Sistema de Leilão XXX foi feito com sucesso!");
		enviarEmailSucesso(pessoaCadastrada);
		
		return pessoaCadastrada;
	}
	
	private void enviarEmailSucesso(Pessoa pessoa) {
		Context context = new Context();
		context.setVariable("nome", pessoa.getNome());
		emailService.emailTemplate(pessoa.getEmail(), "Cadastro Sucesso",
				context, "cadastroSucesso"); //DPS BOTAR ESSE CADASTRO SUCESSO NO messages.properties
		// colocar esse cadastro sucesso num arquivo de contantes com static final
	}
	
	public Pessoa alterar(Pessoa pessoa) {
		Pessoa pessoaBanco = pessoaRepository.findById(pessoa.getId())
				.orElseThrow(() -> new NotFoundException
				(messageSource.getMessage("pessoa.notfound",
						new Object[] { pessoa.getId() }, LocaleContextHolder.getLocale())));
		pessoaBanco.setNome(pessoa.getNome());
		pessoaBanco.setEmail(pessoa.getEmail());
		return pessoaRepository.save(pessoaBanco);
	}
	
	public void excluir(Long id) {
		Pessoa pessoaBanco= buscarPorId(id);
		pessoaRepository.delete(pessoaBanco);
	}

	public Pessoa buscarPorId(Long id) {
		Pessoa pessoaBanco = pessoaRepository.findById(id)
				.orElseThrow(() -> new NotFoundException
				(messageSource.getMessage("pessoa.notfound",
						new Object[] { id }, LocaleContextHolder.getLocale())));
		enviarEmailSucesso(pessoaBanco);
		return pessoaRepository.save(pessoaBanco);
	}
	
	public Page<Pessoa> buscarTodos(Pageable pageable){
		return pessoaRepository.findAll(pageable);
	}
	
	public Pessoa inserirPerfis(PessoaRequestDTO pessoaDto) {
		Perfil perfilCliente = new Perfil(2L, "cliente");
		PessoaPerfil pp2 = new PessoaPerfil();
		Pessoa pessoa = new Pessoa();
		pp2.setPerfil(perfilCliente);
		pessoa.setPessoaPerfil(Arrays.asList(pp2));
		pessoa.setEmail(pessoaDto.getEmail());
		pessoa.setSenha(pessoaDto.getSenha());
		pessoa.setNome(pessoaDto.getNome());
		return inserir(pessoa);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return pessoaRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Pessoa não encontrada"));
	}
}
