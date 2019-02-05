package com.lhc.mapper.singletonMapper;

import com.lhc.datamodel.entities.security.User;
import com.lhc.dto.UserDto;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class UserSingletonMapper {


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

        mapperFactory.classMap(UserDto.class, User.class)
                .field("username", "username")
                .field("password", "password")
                .field("passwordConfirm", "passwordConfirm")
                .register();

        return mapperFactory.getMapperFacade();

    }

    private static MapperFacade getMapperFacadeDto() {

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(User.class, UserDto.class)
                .field("username", "username")
                .field("password", "password")
                .field("passwordConfirm", "passwordConfirm")
                .register();

        return  mapperFactory.getMapperFacade();
    }


}



