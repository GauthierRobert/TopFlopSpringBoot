package com.lhc.mapper.singletonMapper;

import com.lhc.datamodel.entities.competition.Match;
import com.lhc.dto.MatchDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class MatchSingletonMapper {


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

        mapperFactory.classMap(MatchDto.class, Match.class)
                .field("details", "details")
                .field("competition_ref", "competition_ref")
                .field("creatorUsername", "creatorUsername")
                .field("reference", "reference")
                .register();
        return mapperFactory.getMapperFacade();
    }

    private static MapperFacade getMapperFacadeDto() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Match.class, MatchDto.class)
                .field("details", "details")
                .field("competition_ref", "competition_ref")
                .field("creatorUsername", "creatorUsername")
                .field("reference", "reference")
                .register();
        return mapperFactory.getMapperFacade();
    }

}



