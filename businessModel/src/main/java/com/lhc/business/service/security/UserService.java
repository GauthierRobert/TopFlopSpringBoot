package com.lhc.business.service.security;

import com.lhc.datamodel.entities.security.User;

import javax.security.sasl.AuthenticationException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

public interface UserService {


	User findByUsername(String username);

	User save(User user);

	User update(User user);

	boolean authenticate(String username, String password) throws AuthenticationException;

}
