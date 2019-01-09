package com.lhc.webservices.restServices;

import com.lhc.business.service.security.SecurityService;
import com.lhc.business.service.security.UserService;
import com.lhc.business.service.validation.ValidationService;
import com.lhc.datamodel.entities.security.User;
import com.lhc.dto.UserDto;
import com.lhc.mapper.user.UserMapperHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.security.sasl.AuthenticationException;
import javax.ws.rs.core.MediaType;

@RestController
public class SecurityEndPoint {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    @Qualifier(value = "userValidationService")
    private ValidationService<User> validationService;

    @RequestMapping(
            value = "/signUp",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON)
    public UserDto registration(@RequestBody UserDto userDto) {


        UserMapperHandler userMapperHandler = new UserMapperHandler();
        User userForm = userMapperHandler.createEntityFromDTO(userDto);
        validationService.validate(userForm);

        userService.save(userForm);

        return userDto;

    }


    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON)
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


}
