package com.lhc.business.service.competition.impl;

import com.lhc.business.service.competition.CompetitionService;
import com.lhc.datamodel.entities.SystemData;
import com.lhc.datamodel.entities.competition.Competition;
import com.lhc.datamodel.entities.competition.manyToMany.UserCompetition;
import com.lhc.datamodel.entities.image.ImageCompetition;
import com.lhc.datamodel.entities.security.User;
import com.lhc.datamodel.enumeration.Role;
import com.lhc.datamodel.repository.competition.CompetitionRepository;
import com.lhc.datamodel.repository.competition.UserCompetitionRepository;
import com.lhc.datamodel.repository.image.ImageCompetitionRepository;
import com.lhc.datamodel.repository.security.RoleRepository;
import com.lhc.datamodel.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.lhc.datamodel.entities.competition.manyToMany.UserCompetition.player;
import static com.lhc.datamodel.entities.competition.manyToMany.UserCompetition.spectator;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    private CompetitionRepository competitionRepository;
    private UserCompetitionRepository userCompetitionRepository;
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private ImageCompetitionRepository imageCompetitionRepository;

    @Autowired
    public CompetitionServiceImpl(CompetitionRepository competitionRepository, UserCompetitionRepository userCompetitionRepository, RoleRepository roleRepository, UserRepository userRepository, ImageCompetitionRepository imageCompetitionRepository) {
        this.competitionRepository = competitionRepository;
        this.userCompetitionRepository = userCompetitionRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.imageCompetitionRepository = imageCompetitionRepository;
    }

    @Override
    public boolean alreadyExist(String ref) {

        Competition competition = findByReference(ref);

        return competition != null;

    }

    @Override
    @Transactional
    public Competition saveOrUpdate(Competition competition) throws NoSuchAlgorithmException {

        boolean alreadyExist = false;
        Competition competitionDB = null;
        SystemData systemData = SystemData.systemData(UUID.randomUUID().toString(), competition.getSystemData().getCreatedBy());
        if (competition.getSystemData().getReference() != null) {
            competitionDB = findByReference(competition.getSystemData().getReference());
            alreadyExist = isAlreadyExist(competitionDB);
        }

        if (!alreadyExist) {
            competition.setPassword(sha256(competition.getPassword()));
            User user = userRepository.findByUsername(competition.getSystemData().getCreatedBy());
            user.setRoles(new HashSet<>(Arrays.asList(roleRepository.findByName(Role.ROLE_ADMIN.name()))));
            userRepository.save(user);
            competition.setSystemData(systemData);
            competition = competitionRepository.save(competition);
            userCompetitionRepository.save(player(user, competition));
        } else {
            systemData = SystemData.updated(competitionDB.getSystemData(), competition.getSystemData().getModifiedBy());
            populateCompetitionDB(competition, competitionDB);
            competitionDB.setSystemData(systemData);
            competition = competitionRepository.save(competitionDB);
        }

        if (competition.getImageCompetition() == null) {
            ImageCompetition imageCompetition = ImageCompetition.imageCompetition(null, competition.getSystemData().getReference(), competition);
            imageCompetitionRepository.save(imageCompetition);
        }

        return competition;
    }

    private void populateCompetitionDB(Competition competition, Competition competitionInDB) {
        competitionInDB.setDataName(competition.getDataName());
        competitionInDB.setDetails(competition.getDetails());
        competitionInDB.setTopFlopDetails(competition.getTopFlopDetails());
        competitionInDB.setRules(competition.getRules());
    }

    private boolean isAlreadyExist(Competition competition) {
        return competition != null;
    }


    @Override
    @Transactional
    public Competition addUser(User user, String postedName, String postedPassword, boolean isPlayer) throws NoSuchAlgorithmException {

        Competition competition = competitionRepository.findByName(postedName);

        if (competition == null) {
            return null;
        }

        if (isPlayer) {
            String sha256Password = competition.getPassword();
            String postedSha256Password = sha256(postedPassword);

            if (sha256Password.equals(postedSha256Password)) {
                userCompetitionRepository.save(player(user, competition));
                return competition;
            } else {
                return null;
            }
        } else {
            userCompetitionRepository.save(spectator(user, competition));
            return competition;
        }
    }


    @Override
    @Transactional
    public List<Competition> findAllByUsername(String username) {
        return competitionRepository.findAllByUsername(username);
    }

    @Override
    public void deleteUserFromCompetition(String username, String competition_ref) {
        UserCompetition uc = userCompetitionRepository.findByUsernameAndCompetitionReference(username, competition_ref);
        userCompetitionRepository.delete(uc);
    }

    @Override
    public void deleteCompetition(String competition_ref) {
        competitionRepository.deleteByReference(competition_ref);
    }

    @Override
    public Competition findByReference(String ref) {
        return competitionRepository.findByReference(ref);
    }

    @Override
    public Competition findByName(String name) {
        return competitionRepository.findByName(name);
    }

    @Override
    public List<String> findUsersByCompetition(String ref) {
        return competitionRepository.findPlayerByCompetition(ref).stream().sorted().collect(Collectors.toList());
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
