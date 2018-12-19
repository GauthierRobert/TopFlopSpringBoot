package com.lhc.business.service.impl;

import com.lhc.business.dto.Competition;
import com.lhc.business.enumeration.RoleType;
import com.lhc.business.service.CompetitionService;
import com.lhc.business.service.mapper.MapperHandler;
import com.lhc.datamodel.entities.CompetitionRecord;
import com.lhc.datamodel.entities.security.User;
import com.lhc.datamodel.repository.CompetitionRepository;
import com.lhc.datamodel.repository.Security.RoleRepository;
import com.lhc.datamodel.repository.Security.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    private CompetitionRepository competitionRepository;
    private MapperHandler mapperHandler;
    private RoleRepository roleRepository;
    private UserRepository userRepository;


    @Autowired
    public CompetitionServiceImpl(CompetitionRepository competitionRepository, MapperHandler mapperHandler, RoleRepository roleRepository, UserRepository userRepository) {
        this.competitionRepository = competitionRepository;
        this.mapperHandler = mapperHandler;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public CompetitionRecord createCompetition(Competition competition, User user) throws NoSuchAlgorithmException {

        competition.setReference(competition.getName());

        if (competition.getConfirmedPassword().equals(competition.getPassword())) {

            CompetitionRecord competitionRecord = mapperHandler.mapCompetitionRecord(competition, new CompetitionRecord());
            competitionRecord.setPassword(sha256(competition.getPassword()));
            user.setRoles(new HashSet<>(Arrays.asList(roleRepository.findByName(RoleType.ROLE_ADMIN.name()))));
            competitionRecord.getAllowedUsers().add(user);

            userRepository.save(user);

            return competitionRepository.save(competitionRecord);

        } else {
            return null;
        }

    }



    @Override
    @Transactional
    public CompetitionRecord addUserToCompetition(User user, String postedName, String postedPassword) throws NoSuchAlgorithmException {

        CompetitionRecord competitionRecord = competitionRepository.findByReference(postedName);

        if (competitionRecord == null)
        {
            return null;
        }

        String sha256Password = competitionRecord.getPassword();
        String postedSha256Password = sha256(postedPassword);

        if(sha256Password.equals(postedSha256Password)){
            competitionRecord.getAllowedUsers().add(user);
            return competitionRepository.save(competitionRecord);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public List<Competition> findAllByUser (User user){

        List<CompetitionRecord> competitionRecords = competitionRepository.findAllByUser(user);
        List<Competition> competitions = new ArrayList<>();
        competitionRecords.forEach(competitionRecord -> {
            Competition competition = new Competition();
            competition = mapperHandler.mapCompetition(competitionRecord, competition);
            competitions.add(competition);
        });

        return competitions;
    }

    @Override
    public CompetitionRecord findByReference(String ref) {
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
