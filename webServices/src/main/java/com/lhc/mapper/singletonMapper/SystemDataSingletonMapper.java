package com.lhc.mapper.singletonMapper;

import com.lhc.datamodel.entities.SystemData;
import com.lhc.dto.SystemDataDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class SystemDataSingletonMapper {


    /** Instance unique pré-initialisée */
    private static MapperFacade INSTANCE_ENTITY = getMapperFacadeEntity();

    /** Point d'accès pour l'instance unique du singleton */
    public static MapperFacade getInstanceEntity()
    {
        return INSTANCE_ENTITY;
    }


    /** Instance unique pré-initialisée */
    private static MapperFacade INSTANCE_DTO = getMapperFacadeDto();

    /** Point d'accès pour l'instance unique du singleton */
    public static MapperFacade getInstanceDto()
    {
        return INSTANCE_DTO;
    }


    private static MapperFacade getMapperFacadeEntity() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(SystemDataDto.class, SystemData.class)
                .field("reference", "reference")
                .field("createdBy", "createdBy")
                .field("modifiedBy", "modifiedBy")
                .register();
        return mapperFactory.getMapperFacade();
    }

    private static MapperFacade getMapperFacadeDto() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(SystemData.class, SystemDataDto.class)
                .field("reference", "reference")
                .field("createdBy", "createdBy")
                .field("modifiedBy", "modifiedBy")
                .register();
        return mapperFactory.getMapperFacade();
    }

}



