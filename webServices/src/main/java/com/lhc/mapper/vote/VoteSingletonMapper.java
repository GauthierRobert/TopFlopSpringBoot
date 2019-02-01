package com.lhc.mapper.vote;

import com.lhc.datamodel.entities.Vote;
import com.lhc.dto.VoteDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class VoteSingletonMapper {


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

        mapperFactory.classMap(VoteDto.class, Vote.class)
                .field("reference", "reference")
                .field("name", "name")
                .field("points", "points")
                .field("indication", "indication")
                .field("competition_ref", "competition_ref")
                .register();

        return  mapperFactory.getMapperFacade();
    }

    private static MapperFacade getMapperFacadeDto() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Vote.class, VoteDto.class)
                .field("reference", "reference")
                .field("name", "name")
                .field("points", "points")
                .field("indication", "indication")
                .field("competition_ref", "competition_ref")
                .register();

        return  mapperFactory.getMapperFacade();
    }


}



