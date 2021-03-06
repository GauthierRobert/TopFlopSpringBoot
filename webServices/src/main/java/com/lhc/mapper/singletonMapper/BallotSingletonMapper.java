package com.lhc.mapper.singletonMapper;

import com.lhc.datamodel.entities.competition.Ballot;
import com.lhc.dto.BallotDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class BallotSingletonMapper {


    /**
     * Instance unique pré-initialisée
     */
    private static MapperFacade INSTANCE_ENTITY = getMapperFacadeEntity();

    /**
     * Point d'accès pour l'instance unique du singleton
     */
    public static MapperFacade getInstanceEntity() {
        return INSTANCE_ENTITY;
    }


    /**
     * Instance unique pré-initialisée
     */
    private static MapperFacade INSTANCE_DTO = getMapperFacadeDto();

    /**
     * Point d'accès pour l'instance unique du singleton
     */
    public static MapperFacade getInstanceDto() {
        return INSTANCE_DTO;
    }


    private static MapperFacade getMapperFacadeEntity() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(BallotDto.class, Ballot.class)
                     .mapNulls(false).mapNullsInReverse(false)
                     .field("reference", "reference")
                     .field("match_ref", "match_ref")
                     .field("competition_ref", "competition_ref")
                     .field("commentTop", "commentTop")
                     .field("commentFlop", "commentFlop")
                     .field("counted", "counted")

                     .register();

        return mapperFactory.getMapperFacade();
    }

    private static MapperFacade getMapperFacadeDto() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Ballot.class, BallotDto.class)
                     .mapNulls(false).mapNullsInReverse(false)
                     .field("reference", "reference")
                     .field("match_ref", "match_ref")
                     .field("competition_ref", "competition_ref")
                     .field("commentTop", "commentTop")
                     .field("commentFlop", "commentFlop")
                     .field("counted", "counted")
                     .register();

        return mapperFactory.getMapperFacade();
    }


}



