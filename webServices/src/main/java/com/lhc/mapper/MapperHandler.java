package com.lhc.business.service.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface MapperHandler<ENTITY,DTO> {

    DTO mapToDto(ENTITY entity, DTO dto);
    ENTITY mapToEntity(DTO dto, ENTITY entity);

    default List mapToListEntities(final Collection dtos) {
        return dtos.stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());
    }

    default List mapToListDtos(final Collection entities) {
        return entities.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

}
