package com.lhc.webservices.restServices.impl;

import com.lhc.business.service.security.SecurityService;
import com.lhc.business.service.security.UserService;
import com.lhc.business.service.validation.ValidationService;
import com.lhc.datamodel.entities.security.User;
import com.lhc.dto.UserDto;
import com.lhc.mapper.mapperHandler.UserMapperHandler;
import com.lhc.webservices.restServices.SecurityEndPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.security.sasl.AuthenticationException;
@RestController
public class SecurityEndPointImpl implements SecurityEndPoint {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    @Qualifier(value = "userValidationService")
    private ValidationService<User> validationService;

    @Override
    public UserDto registration(@RequestBody UserDto userDto) {


        UserMapperHandler userMapperHandler = new UserMapperHandler();
        User userForm = userMapperHandler.createEntityFromDTO(userDto);
        validationService.validate(userForm);

        userService.save(userForm);

        return userDto;

    }


    @Override
    public Boolean login(@RequestBody UserDto userDto) {

        boolean authenticate = false;
        try {
            authenticate = userService.authenticate(
                    userDto.getUsername(),
                    userDto.getPassword());
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        return authenticate;

    }

    @Override
    public Boolean changePassword(@RequestBody UserDto userDto,
                                  @RequestParam("newPassword") String newPassword,
                                  @RequestParam("newConfirmedPassword") String newConfirmedPassword) {

        boolean authenticate = false;
        try {
            authenticate = userService.authenticate(
                    userDto.getUsername(),
                    userDto.getPassword());
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        if(authenticate){
            if (newPassword.equals(newConfirmedPassword)){
                UserMapperHandler userMapperHandler = new UserMapperHandler();
                User userForm = userMapperHandler.createEntityFromDTO(userDto);
                validationService.validate(userForm);

                userService.save(userForm);

                return true;

            }
            return false;
        } else {
            return false;
        }
    }


}
