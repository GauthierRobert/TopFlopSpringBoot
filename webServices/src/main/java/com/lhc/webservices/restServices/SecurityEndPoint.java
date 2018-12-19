package com.lhc.webservices.restServices;

import com.lhc.business.service.security.SecurityService;
import com.lhc.business.service.security.UserService;
import com.lhc.business.validator.UserValidator;
import com.lhc.datamodel.entities.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;

@ApplicationPath("/authentication")
@RestController
public class SecurityEndPoint {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(
            value = "/signUp",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON )
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "signUp";
    }

    @RequestMapping(
            value = "/signUp",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON)
    public String registration(@RequestBody User userForm, BindingResult bindingResult)
            throws ParseException {

        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "Error";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "Ok";
    }


    @RequestMapping(
            value = "/login",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

}
