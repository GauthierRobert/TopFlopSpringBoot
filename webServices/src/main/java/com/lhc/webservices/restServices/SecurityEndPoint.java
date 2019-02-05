package com.lhc.webservices.restServices;

import com.lhc.dto.UserDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
@RestController
public interface SecurityEndPoint {

    @RequestMapping(
            value = "/signUp",
            method = RequestMethod.POST)
    UserDto registration(@RequestBody UserDto userDto);

    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST)
    Boolean login(@RequestBody UserDto userDto);

    @RequestMapping(
            value = "/change/password",
            method = RequestMethod.POST)
    Boolean changePassword(@RequestBody UserDto userDto,
                           @RequestParam("newPassword") String newPassword,
                           @RequestParam("newConfirmedPassword") String newConfirmedPassword);
}
