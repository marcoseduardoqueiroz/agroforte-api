package com.agroforte.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = { "pessoaFisica", "parcelas" })
@Entity
@Table(name = "operacao")
public class Operacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_emissao")
    private LocalDate dataEmissao;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "quantidade_parcelas")
    private Integer quantidadeParcelas;

    @Column(name = "data_primeira_parcela")
    private LocalDate dataPrimeiraParcela;

    @Column(name = "tempo_carencia")
    private Integer tempoCarencia;

    @Column(name = "valor_operacao")
    private BigDecimal valorOperacao;

    @Column(name = "taxa_mensal")
    private BigDecimal taxaMensal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_fisica_id", nullable = false)
    private PessoaFisica pessoaFisica;

    @OneToMany(mappedBy = "operacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Parcela> parcelas;
}
