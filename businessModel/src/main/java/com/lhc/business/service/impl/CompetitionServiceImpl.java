package com.lhc.business.service.impl;

import com.lhc.datamodel.enumeration.RoleType;
import com.lhc.business.service.CompetitionService;
import com.lhc.datamodel.entities.Competition;
import com.lhc.datamodel.entities.security.User;
import com.lhc.datamodel.repository.CompetitionRepository;
import com.lhc.datamodel.repository.Security.RoleRepository;
import com.lhc.datamodel.repository.Security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    private CompetitionRepository competitionRepository;
    private RoleRepository roleRepository;
    private UserRepository userRepository;


    @Autowired
    public CompetitionServiceImpl(CompetitionRepository competitionRepository, RoleRepository roleRepository, UserRepository userRepository) {
        this.competitionRepository = competitionRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Competition createCompetition(Competition competition, User user) throws NoSuchAlgorithmException {

        competition.setReference(competition.getName());


        competition.setPassword(sha256(competition.getPassword()));
        user.setRoles(new HashSet<>(Arrays.asList(roleRepository.findByName(RoleType.ROLE_ADMIN.name()))));
        competition.getAllowedUsers().add(user);

        userRepository.save(user);

        return competitionRepository.save(competition);

    }



    @Override
    @Transactional
    public Competition addUserToCompetition(User user, String postedName, String postedPassword) throws NoSuchAlgorithmException {

        Competition competition = competitionRepository.findByReference(postedName);

        if (competition == null)
        {
            return null;
        }

        String sha256Password = competition.getPassword();
        String postedSha256Password = sha256(postedPassword);

        if(sha256Password.equals(postedSha256Password)){
            competition.getAllowedUsers().add(user);
            return competitionRepository.save(competition);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public List<Competition> findAllByUser (User user){

        return competitionRepository.findAllByUser(user);
    }


    @Override
    @Transactional
    public List<Competition> findAllByUsername (String username){

        return competitionRepository.findAllByUsername(username);
    }


    @Override
    public Competition findByReference(String ref) {
        return competitionRepository.findByReference(ref);
    }

    private String sha256(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(data.getBytes());
        return bytesToHex(md.digest());
    }

    private String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte byt : bytes) result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }


}
