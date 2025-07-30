package com.agroforte.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OperacaoResponse {
	private Long id;
	private LocalDate dataInicio;
	private LocalDate dataEmissao;
	private LocalDate dataFim;
	private Integer quantidadeParcelas;
	private LocalDate dataPrimeiraParcela;
	private Integer tempoCarencia;
	private BigDecimal valorOperacao;
	private BigDecimal taxaMensal;
}
