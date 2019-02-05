package com.lhc.mapper.mapperHandler;

import com.lhc.datamodel.entities.security.User;
import com.lhc.dto.UserDto;
import com.lhc.mapper.MapperHandler;
import com.lhc.mapper.singletonMapper.UserSingletonMapper;
import ma.glasnost.orika.MapperFacade;

public class UserMapperHandler implements MapperHandler<User, UserDto> {

    @Override
    public User mapToEntity(UserDto userDto, User user) {

        if (user ==null){
            user = new User();
        }
        MapperFacade mapper = UserSingletonMapper.getInstanceEntity();
        mapper.map(userDto, user);

        return user;
    }

    @Override
    public User createEntityFromDTO(UserDto userDto) {
        return mapToEntity(userDto, new User());
    }

    @Override
    public UserDto createDTOFromEntity(User user) {
        return mapToDto(user, new UserDto());
    }


    @Override
    public UserDto mapToDto(User user, UserDto userDto) {

        if (userDto == null) {
            userDto = new UserDto();
        }

        MapperFacade mapper = UserSingletonMapper.getInstanceDto();
        mapper.map(user, userDto);

        return userDto;
    }
    
    
}
