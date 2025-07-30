package com.agroforte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agroforte.entity.Operacao;

@Repository
public interface OperacaoRepository extends JpaRepository<Operacao, Long> {
	// Métodos customizados podem ser adicionados aqui
}
