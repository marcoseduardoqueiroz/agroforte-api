package com.agroforte.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.agroforte.dto.PessoaFisicaRequest;
import com.agroforte.dto.PessoaFisicaResponse;
import com.agroforte.entity.PessoaFisica;
import com.agroforte.mapper.PessoaFisicaMapper;
import com.agroforte.repository.PessoaFisicaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PessoaFisicaService {

	private final PessoaFisicaRepository repository;
	private final PessoaFisicaMapper mapper;

	public PessoaFisicaResponse criar(PessoaFisicaRequest request) {
		PessoaFisica pessoa = mapper.toEntity(request);
		PessoaFisica pessoaSalva = repository.save(pessoa);
		return mapper.toResponse(pessoaSalva);
	}

	public PessoaFisicaResponse buscarPorId(Long id) {
		PessoaFisica pessoa = repository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Pessoa não encontrada com id: " + id));
		return mapper.toResponse(pessoa);
	}
}
