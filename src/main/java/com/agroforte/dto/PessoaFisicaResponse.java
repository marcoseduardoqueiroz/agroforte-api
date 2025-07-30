package com.agroforte.dto;

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
public class PessoaFisicaResponse {
	private Long id;
	private String nome;
	private String email;
	private String cpf;
	private String rg;
	private String celular;
	private String maritalStatus;
	private String genero;
	private LocalDate dataNascimento;
	private String nomeMae;
	private String nacionalidade;
}
