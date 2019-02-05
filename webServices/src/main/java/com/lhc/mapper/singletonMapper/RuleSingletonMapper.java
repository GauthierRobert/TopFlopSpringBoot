package com.lhc.mapper.singletonMapper;

import com.lhc.datamodel.entities.rules.Rule;
import com.lhc.dto.RuleDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class RuleSingletonMapper {

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

        mapperFactory.classMap(RuleDto.class, Rule.class)
                .field("description", "description")
                .field("label", "label")
                .field("points", "points")
                .field("indication", "indication")
                .register();

        return mapperFactory.getMapperFacade();

    }

    private static MapperFacade getMapperFacadeDto() {

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Rule.class, RuleDto.class)
                .field("description", "description")
                .field("label", "label")
                .field("points", "points")
                .field("indication", "indication")
                .register();

        return mapperFactory.getMapperFacade();

    }


}
