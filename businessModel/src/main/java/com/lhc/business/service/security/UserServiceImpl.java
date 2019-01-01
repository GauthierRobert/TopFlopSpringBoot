package com.lhc.business.service.security;

import com.lhc.business.enumeration.RoleType;
import com.lhc.datamodel.entities.security.User;
import com.lhc.datamodel.repository.Security.RoleRepository;
import com.lhc.datamodel.repository.Security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import javax.transaction.Transactional;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;


@Service("userService")
public class UserServiceImpl implements UserService {
	
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
    @Override
    @Transactional
    public User save(User user) {

        validate(user);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(Collections.singletonList(roleRepository.findByName(RoleType.ROLE_USER.name()))));
        user = userRepository.save(user);

        return user;

    }

    private void validate(User user) {
        if (user !=null) {
            String username = user.getUsername();
            if (username != null) {
                User alreadyExistUser = findByUsername(username);
                if (alreadyExistUser != null){
                    throw new RuntimeException("User already exists");
                }
            } else {
                throw new RuntimeException("Encode a username");
            }
        } else {
            throw new RuntimeException("Unexpected error");
        }
    }

    @Override
    public boolean authenticate(String username, String password) throws AuthenticationException {


        User user = findByUsername(username);

        if (user == null){
            throw new AuthenticationException("Authentication failed");
        }

        String bCryptUserPassword = user.getPassword();
        boolean matches  = bCryptPasswordEncoder.matches(password, bCryptUserPassword);

        if (matches) {
            if (username != null && username.equalsIgnoreCase(user.getUsername())) {
                return true;
            }
        }

        throw new AuthenticationException("Authentication failed");

    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    

}
