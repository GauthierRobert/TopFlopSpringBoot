package com.lhc.business.service.security;

import com.lhc.business.enumeration.RoleType;
import com.lhc.datamodel.entities.security.User;
import com.lhc.datamodel.repository.Security.RoleRepository;
import com.lhc.datamodel.repository.Security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
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

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        user.setRoles(new HashSet<>(Arrays.asList(roleRepository.findByName(RoleType.ROLE_USER.name()))));

        user = userRepository.save(user);

        return user;

    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    

}
