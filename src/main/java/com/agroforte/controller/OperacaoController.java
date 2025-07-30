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
import com.agroforte.dto.OperacaoRequest;
import com.agroforte.dto.OperacaoResponse;
import com.agroforte.service.OperacaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/operacoes")
@Validated
public class OperacaoController {

	private final OperacaoService service;
	private final Logger logger;

	public OperacaoController(OperacaoService service, LoggerFactoryComponent loggerFactory) {
		this.service = service;
		this.logger = loggerFactory.getLogger(OperacaoController.class);
	}

	@PostMapping
	public ResponseEntity<OperacaoResponse> criar(@Valid @RequestBody OperacaoRequest request) {
		logger.info("Recebida requisição para criar Operação: {}", request);
		OperacaoResponse response = service.criar(request);
		logger.info("Operação criada com sucesso: id={}, valor={}", response.getId(), response.getValorOperacao());
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<OperacaoResponse> buscar(@PathVariable Long id) {
		logger.info("Recebida requisição para buscar Operação com id={}", id);
		OperacaoResponse response = service.buscarPorId(id);
		if (response == null) {
			logger.warn("Operação com id={} não encontrada", id);
			return ResponseEntity.notFound().build();
		}
		logger.info("Operação encontrada: {}", response);
		return ResponseEntity.ok(response);
	}
}
