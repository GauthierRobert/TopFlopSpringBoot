package com.lhc.business.service.security;

import com.lhc.datamodel.entities.security.User;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

public interface UserService {


	User findByUsername(String username);

	User save(User user) throws ParseException;

}
