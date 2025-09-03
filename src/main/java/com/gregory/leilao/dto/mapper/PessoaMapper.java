package com.gregory.leilao.dto.mapper;

import org.modelmapper.ModelMapper;

import com.gregory.leilao.dto.PessoaRequestDTO;
import com.gregory.leilao.dto.PessoaResponseDTO;
import com.gregory.leilao.model.Pessoa;

public class PessoaMapper {

    public static Pessoa toPessoa(PessoaRequestDTO dto) {
        return new ModelMapper().map(dto, Pessoa.class);
    }

    public static PessoaResponseDTO toDto(Pessoa pessoa) {
        return new ModelMapper().map(pessoa, PessoaResponseDTO.class);
    }
}
