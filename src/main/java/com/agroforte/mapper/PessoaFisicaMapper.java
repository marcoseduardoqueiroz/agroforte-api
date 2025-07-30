package com.agroforte.mapper;

import org.mapstruct.Mapper;

import com.agroforte.dto.PessoaFisicaRequest;
import com.agroforte.dto.PessoaFisicaResponse;
import com.agroforte.entity.PessoaFisica;

@Mapper(componentModel = "spring")
public interface PessoaFisicaMapper {

	PessoaFisica toEntity(PessoaFisicaRequest request);

	PessoaFisicaResponse toResponse(PessoaFisica entity);
}
