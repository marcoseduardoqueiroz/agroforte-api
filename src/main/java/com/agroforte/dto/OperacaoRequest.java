package com.agroforte.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
public class OperacaoRequest {

	@NotNull
	private LocalDate dataInicio;

	@NotNull
	private LocalDate dataEmissao;

	private LocalDate dataFim;

	@NotNull
	@Min(1)
	private Integer quantidadeParcelas;

	@NotNull
	private LocalDate dataPrimeiraParcela;

	private Integer tempoCarencia;

	@NotNull
	@DecimalMin("0.0")
	private BigDecimal valorOperacao;

	@DecimalMin("0.0")
	private BigDecimal taxaMensal;
}
