package com.bahar.crm.service.mapper;

import org.springframework.stereotype.Service;

import java.util.List;

public interface EntityMapper<DTO, ENTITY> {
    ENTITY toEntity(DTO dto);

    DTO toDto(ENTITY entity);

    List<ENTITY> toEntity(List<DTO> dtoList);

    List <DTO> toDto(List<ENTITY> entityList);
}
