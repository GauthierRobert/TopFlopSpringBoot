package com.lhc.mapper.competition;

import com.lhc.datamodel.entities.Competition;
import com.lhc.datamodel.entities.rules.Rule;
import com.lhc.dto.CompetitionDto;
import com.lhc.dto.RuleDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class CompetitionSingletonMapper {


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

        mapperFactory.classMap(CompetitionDto.class, Competition.class)
                .field("reference", "reference")
                .field("name", "name")
                .field("season", "season")
                .field("division", "division")
                .field("password", "password")
                .field("confirmedPassword", "confirmedPassword")
                .field("creatorUsername", "creatorUsername")
                .register();

        return  mapperFactory.getMapperFacade();
    }

    private static MapperFacade getMapperFacadeDto() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Competition.class, CompetitionDto.class)
                .field("reference", "reference")
                .field("name", "name")
                .field("season", "season")
                .field("division", "division")
                .field("password", "password")
                .field("confirmedPassword", "confirmedPassword")
                .field("creatorUsername", "creatorUsername")
                .register();

        return  mapperFactory.getMapperFacade();
    }


}



