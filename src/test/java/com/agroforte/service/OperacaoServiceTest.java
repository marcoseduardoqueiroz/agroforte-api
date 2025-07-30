package com.agroforte.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.agroforte.dto.OperacaoRequest;
import com.agroforte.dto.OperacaoResponse;
import com.agroforte.entity.Operacao;
import com.agroforte.mapper.OperacaoMapper;
import com.agroforte.repository.OperacaoRepository;

class OperacaoServiceTest {

	@Mock
	private OperacaoRepository repository;

	@Mock
	private OperacaoMapper mapper;

	@InjectMocks
	private OperacaoService service;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void criar_deveSalvarOperacaoERetornarResponse() {
		OperacaoRequest request = new OperacaoRequest();
		// preencha os dados do request se necessário

		Operacao entity = new Operacao();
		Operacao savedEntity = new Operacao();
		OperacaoResponse response = new OperacaoResponse();

		when(mapper.toEntity(request)).thenReturn(entity);
		when(repository.save(entity)).thenReturn(savedEntity);
		when(mapper.toResponse(savedEntity)).thenReturn(response);

		OperacaoResponse result = service.criar(request);

		assertNotNull(result);
		verify(mapper).toEntity(request);
		verify(repository).save(entity);
		verify(mapper).toResponse(savedEntity);
	}

	@Test
	void buscarPorId_deveRetornarResponseQuandoEncontrar() {
		Long id = 1L;
		Operacao entity = new Operacao();
		OperacaoResponse response = new OperacaoResponse();

		when(repository.findById(id)).thenReturn(Optional.of(entity));
		when(mapper.toResponse(entity)).thenReturn(response);

		OperacaoResponse result = service.buscarPorId(id);

		assertNotNull(result);
		verify(repository).findById(id);
		verify(mapper).toResponse(entity);
	}

	@Test
	void buscarPorId_deveLancarExcecaoQuandoNaoEncontrar() {
		Long id = 1L;

		when(repository.findById(id)).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> {
			service.buscarPorId(id);
		});

		verify(repository).findById(id);
		verifyNoMoreInteractions(mapper);
	}
}
