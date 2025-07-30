package com.agroforte.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.agroforte.dto.OperacaoRequest;
import com.agroforte.dto.OperacaoResponse;
import com.agroforte.entity.Operacao;
import com.agroforte.mapper.OperacaoMapper;
import com.agroforte.repository.OperacaoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OperacaoService {

	private final OperacaoRepository repository;
	private final OperacaoMapper mapper;

	public OperacaoResponse criar(OperacaoRequest request) {
		Operacao operacao = mapper.toEntity(request);
		Operacao operacaoSalva = repository.save(operacao);
		return mapper.toResponse(operacaoSalva);
	}

	public OperacaoResponse buscarPorId(Long id) {
		Operacao operacao = repository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Operação não encontrada com id: " + id));
		return mapper.toResponse(operacao);
	}
}
