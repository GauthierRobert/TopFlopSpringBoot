package com.lhc.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface MapperHandler<ENTITY,DTO> {

    DTO mapToDto(ENTITY entity, DTO dto);
    ENTITY mapToEntity(DTO dto, ENTITY entity);

    ENTITY createEntityFromDTO(DTO dto);
    DTO createDTOFromEntity(ENTITY entity);

    default List<ENTITY> mapToListEntities(final List<DTO> dtos) {
        return dtos.stream()
                .map(dto -> this.createEntityFromDTO(dto))
                .collect(Collectors.toList());
    }

    default List<DTO> mapToListDtos(final List<ENTITY> entities) {
        return entities.stream()
                .map(entity -> this.createDTOFromEntity(entity))
                .collect(Collectors.toList());
    }
}
