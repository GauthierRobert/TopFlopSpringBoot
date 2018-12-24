package com.lhc.business.service.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface MapperHandler<O,OR> {

    OR mapToDto(O object, OR objectRecord);
    O mapToEntity(OR objectRecord, O object);

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
