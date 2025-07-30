package com.agroforte.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.agroforte.config.LoggerFactoryComponent;
import com.agroforte.dto.PessoaFisicaRequest;
import com.agroforte.dto.PessoaFisicaResponse;
import com.agroforte.service.PessoaFisicaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class PessoaFisicaControllerTest {

	private MockMvc mockMvc;

	@Mock
	private PessoaFisicaService service;

	@Mock
	private LoggerFactoryComponent loggerFactory;

	@Mock
	private Logger logger;

	private PessoaFisicaController controller;

	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		// Mocka o factory para retornar o logger mockado
		when(loggerFactory.getLogger(PessoaFisicaController.class)).thenReturn(logger);

		// Cria o controller injetando service e loggerFactory (ajuste conforme seu
		// construtor)
		controller = new PessoaFisicaController(service, loggerFactory);

		mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper)).build();
	}

	@Test
	void criarPessoaFisica_ReturnsCreated() throws Exception {
		PessoaFisicaRequest request = PessoaFisicaRequest.builder().nome("João Silva").email("joao.silva@email.com")
				.cpf("12345678909").dataNascimento(LocalDate.of(1990, 1, 1)).build();

		PessoaFisicaResponse response = new PessoaFisicaResponse();
		response.setId(1L);
		response.setNome("João Silva");

		when(service.criar(any(PessoaFisicaRequest.class))).thenReturn(response);

		mockMvc.perform(post("/api/pessoas").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value(1L)).andExpect(jsonPath("$.nome").value("João Silva"));
	}
}
