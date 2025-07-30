package com.agroforte.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

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
import com.agroforte.dto.OperacaoResponse;
import com.agroforte.service.OperacaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class OperacaoControllerTest {

	private MockMvc mockMvc;

	@Mock
	private OperacaoService service;

	@Mock
	private LoggerFactoryComponent loggerFactory;

	@Mock
	private Logger logger;

	private OperacaoController controller;

	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		// Configurar o ObjectMapper com suporte a LocalDate
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());

		// Forçar o mock do logger
		when(loggerFactory.getLogger(OperacaoController.class)).thenReturn(logger);

		// Criar controller manualmente com mocks
		controller = new OperacaoController(service, loggerFactory);

		mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper)).build();
	}

	@Test
	void criarOperacao_ReturnsCreated() throws Exception {
		String jsonRequest = """
				{
					"dataInicio": "2025-07-29",
					"dataEmissao": "2025-07-29",
					"quantidadeParcelas": 5,
					"dataPrimeiraParcela": "2025-08-29",
					"valorOperacao": 100.00,
					"taxaMensal": 1.5
				}
				""";

		OperacaoResponse response = new OperacaoResponse();
		response.setId(1L);
		response.setValorOperacao(new BigDecimal("100.00"));

		when(service.criar(any())).thenReturn(response);

		mockMvc.perform(post("/api/operacoes").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(response.getId()))
				.andExpect(jsonPath("$.valorOperacao").value(response.getValorOperacao().doubleValue()));
	}

	@Test
	void buscarOperacao_ReturnsOk() throws Exception {
		Long id = 1L;
		OperacaoResponse response = new OperacaoResponse();
		response.setId(id);
		response.setValorOperacao(new BigDecimal("100.00"));

		when(service.buscarPorId(id)).thenReturn(response);

		mockMvc.perform(get("/api/operacoes/{id}", id)).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(id))
				.andExpect(jsonPath("$.valorOperacao").value(response.getValorOperacao().doubleValue()));
	}

	@Test
	void buscarOperacao_ReturnsNotFound() throws Exception {
		Long id = 999L;
		when(service.buscarPorId(id)).thenReturn(null);

		mockMvc.perform(get("/api/operacoes/{id}", id)).andExpect(status().isNotFound());
	}
}
