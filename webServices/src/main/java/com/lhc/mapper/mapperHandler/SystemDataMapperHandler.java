package com.lhc.mapper.mapperHandler;

import com.lhc.datamodel.entities.SystemData;
import com.lhc.dto.SystemDataDto;
import com.lhc.mapper.MapperHandler;
import ma.glasnost.orika.MapperFacade;

import static com.lhc.dto.SystemDataDto.systemDataDto;
import static com.lhc.mapper.singletonMapper.SystemDataSingletonMapper.getInstanceDto;
import static com.lhc.mapper.singletonMapper.SystemDataSingletonMapper.getInstanceEntity;

public class SystemDataMapperHandler implements MapperHandler<SystemData, SystemDataDto> {


    @Override
    public SystemData mapToEntity(SystemDataDto systemDataDto, SystemData systemData) {

        if (systemData == null) {
            systemData = SystemData.systemData();
        }

        MapperFacade mapper = getInstanceEntity();
        mapper.map(systemDataDto, systemData);


        return systemData;

    }

    @Override
    public SystemData createEntityFromDTO(SystemDataDto systemDataDto) {
        return mapToEntity(systemDataDto, SystemData.systemData());
    }

    @Override
    public SystemDataDto createDTOFromEntity(SystemData systemData) {
        return mapToDto(systemData, systemDataDto());
    }


    @Override
    public SystemDataDto mapToDto(SystemData systemData, SystemDataDto systemDataDto) {

        if (systemDataDto == null) {
            systemDataDto = systemDataDto();
        }


        MapperFacade mapper = getInstanceDto();
        mapper.map(systemData, systemDataDto);

        return systemDataDto;

    }

}
