package com.agroforte.mapper;

import org.mapstruct.Mapper;

import com.agroforte.dto.OperacaoRequest;
import com.agroforte.dto.OperacaoResponse;
import com.agroforte.entity.Operacao;

@Mapper(componentModel = "spring")
public interface OperacaoMapper {

	Operacao toEntity(OperacaoRequest request);

	OperacaoResponse toResponse(Operacao entity);
}
