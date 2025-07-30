package com.agroforte.controller;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agroforte.config.LoggerFactoryComponent;
import com.agroforte.dto.PessoaFisicaRequest;
import com.agroforte.dto.PessoaFisicaResponse;
import com.agroforte.service.PessoaFisicaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pessoas")
@Validated
public class PessoaFisicaController {

	private final PessoaFisicaService service;
	private final Logger logger;

	public PessoaFisicaController(PessoaFisicaService service, LoggerFactoryComponent loggerFactory) {
		this.service = service;
		this.logger = loggerFactory.getLogger(PessoaFisicaController.class);
	}

	@PostMapping
	public ResponseEntity<PessoaFisicaResponse> criar(@Valid @RequestBody PessoaFisicaRequest request) {
		logger.info("Recebida requisição para criar Pessoa Física: {}", request);
		PessoaFisicaResponse response = service.criar(request);
		logger.info("Pessoa criada com sucesso: id={}, nome={}", response.getId(), response.getNome());
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PessoaFisicaResponse> buscar(@PathVariable Long id) {
		logger.info("Recebida requisição para buscar Pessoa Física com id={}", id);
		PessoaFisicaResponse response = service.buscarPorId(id);
		if (response == null) {
			logger.warn("Pessoa Física com id={} não encontrada", id);
			return ResponseEntity.notFound().build();
		}
		logger.info("Pessoa encontrada: {}", response);
		return ResponseEntity.ok(response);
	}
}
