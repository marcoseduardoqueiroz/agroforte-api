package com.agroforte.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pessoa_fisica")
public class PessoaFisica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "cpf", unique = true, length = 11, nullable = false)
    private String cpf;

    @Column(name = "rg")
    private String rg;

    @Column(name = "celular")
    private String celular;

    @Column(name = "marital_status")
    private String maritalStatus;

    @Column(name = "genero")
    private String genero;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "nome_mae")
    private String nomeMae;

    @Column(name = "nacionalidade")
    private String nacionalidade;
}
