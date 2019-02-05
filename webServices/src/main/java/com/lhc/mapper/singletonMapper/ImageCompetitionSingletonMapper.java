package com.lhc.mapper.singletonMapper;

import com.lhc.datamodel.entities.image.ImageCompetition;
import com.lhc.dto.ImageCompetitionDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class ImageCompetitionSingletonMapper {


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

        mapperFactory.classMap(ImageCompetitionDto.class, ImageCompetition.class)
                .field("competition_ref", "competition_ref")
                .field("asBase64", "asBase64")
                .register();

        return  mapperFactory.getMapperFacade();
    }

    private static MapperFacade getMapperFacadeDto() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(ImageCompetition.class, ImageCompetitionDto.class)
                .field("competition_ref", "competition_ref")
                .field("asBase64", "asBase64")
                .register();

        return  mapperFactory.getMapperFacade();
    }


}



