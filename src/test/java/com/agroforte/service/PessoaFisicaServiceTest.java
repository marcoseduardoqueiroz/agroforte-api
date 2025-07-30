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

import com.agroforte.dto.PessoaFisicaRequest;
import com.agroforte.dto.PessoaFisicaResponse;
import com.agroforte.entity.PessoaFisica;
import com.agroforte.mapper.PessoaFisicaMapper;
import com.agroforte.repository.PessoaFisicaRepository;

class PessoaFisicaServiceTest {

	@Mock
	private PessoaFisicaRepository repository;

	@Mock
	private PessoaFisicaMapper mapper;

	@InjectMocks
	private PessoaFisicaService service;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void criar_deveSalvarPessoaERetornarResponse() {
		PessoaFisicaRequest request = new PessoaFisicaRequest();
		// popular request conforme necessário

		PessoaFisica entity = new PessoaFisica();
		PessoaFisica savedEntity = new PessoaFisica();
		PessoaFisicaResponse response = new PessoaFisicaResponse();

		when(mapper.toEntity(request)).thenReturn(entity);
		when(repository.save(entity)).thenReturn(savedEntity);
		when(mapper.toResponse(savedEntity)).thenReturn(response);

		PessoaFisicaResponse result = service.criar(request);

		assertNotNull(result);
		verify(mapper).toEntity(request);
		verify(repository).save(entity);
		verify(mapper).toResponse(savedEntity);
	}

	@Test
	void buscarPorId_deveRetornarResponseQuandoEncontrar() {
		Long id = 1L;
		PessoaFisica entity = new PessoaFisica();
		PessoaFisicaResponse response = new PessoaFisicaResponse();

		when(repository.findById(id)).thenReturn(Optional.of(entity));
		when(mapper.toResponse(entity)).thenReturn(response);

		PessoaFisicaResponse result = service.buscarPorId(id);

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
