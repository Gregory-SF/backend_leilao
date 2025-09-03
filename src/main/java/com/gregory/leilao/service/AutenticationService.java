package com.gregory.leilao.service;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gregory.leilao.dto.LoginDTO;
import com.gregory.leilao.dto.PessoaResponseDTO;
import com.gregory.leilao.model.Pessoa;
import com.gregory.leilao.repository.PessoaRepository;
import com.gregory.leilao.security.AuthPessoaProvider;
import com.gregory.leilao.security.JwtService;

@Service
public class AutenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private PessoaRepository pessoaRepository;
     
    public PessoaResponseDTO autenticar(LoginDTO pessoa) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(pessoa.getEmail(), pessoa.getSenha())
        );
        
        PessoaResponseDTO dto = new PessoaResponseDTO();
        Pessoa pessoaBanco = pessoaRepository.findByEmail(pessoa.getEmail()).get();
        dto.setEmail(pessoaBanco.getEmail());
        dto.setNome(pessoaBanco.getNome());
        dto.setToken(jwtService.generateToken(authentication.getName()));
        return dto;
    }

}

