package com.lhc.business.service.validation;

import com.lhc.business.service.security.UserService;
import com.lhc.datamodel.entities.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Errors;

@Service(value = "userValidationService")
public class UserValidationService extends AbstractValidationService<User> {


    @Autowired
    private UserService userService;

    @Override
    public String validate(User user) {


        if (user.getPassword() == null){
            return "Password empty";
        }

        if (user.getUsername() == null){
            return "Username empty";
        }

        if (userService.findByUsername(user.getUsername()) != null) {
            return "User already exists";
        }


        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            return "Password doesn't match confirmed password";
        }

        return null;

    }
}
