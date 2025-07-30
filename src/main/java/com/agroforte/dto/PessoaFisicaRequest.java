package com.agroforte.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
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
public class PessoaFisicaRequest {

	@NotBlank
	private String nome;

	@Email
	@NotBlank
	private String email;

	@NotBlank
	private String cpf;

	private String rg;
	private String celular;
	private String maritalStatus;
	private String genero;

	@Past
	private LocalDate dataNascimento;

	private String nomeMae;
	private String nacionalidade;
}
