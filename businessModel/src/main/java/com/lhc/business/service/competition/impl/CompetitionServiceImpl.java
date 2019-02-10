package com.lhc.business.service.competition.impl;

import com.lhc.business.service.competition.CompetitionService;
import com.lhc.datamodel.entities.competition.Competition;
import com.lhc.datamodel.entities.image.ImageCompetition;
import com.lhc.datamodel.entities.security.User;
import com.lhc.datamodel.enumeration.RoleType;
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
    public Competition saveOrUpdate(Competition competition, User user) throws NoSuchAlgorithmException {

        boolean alreadyExist = false;
        Competition competitionInDB = null;
        if (competition.getReference() != null) {
            competitionInDB = findByReference(competition.getReference());
            alreadyExist = isAlreadyExist(competitionInDB);
        } else {
            competition.setReference(UUID.randomUUID().toString());
        }

        if (!alreadyExist) {
            competition.setPassword(sha256(competition.getPassword()));
            user.setRoles(new HashSet<>(Arrays.asList(roleRepository.findByName(RoleType.ROLE_ADMIN.name()))));
            userRepository.save(user);
            competition = competitionRepository.save(competition);
            userCompetitionRepository.save(player(user, competition));
        } else {
            competitionInDB.setName(competition.getName());
            competitionInDB.setDivision(competition.getDivision());
            competitionInDB.setReference(competition.getReference());
            competitionInDB.setSeason(competition.getSeason());
            competitionInDB.setWithCommentFlop(competition.getWithCommentFlop());
            competitionInDB.setWithCommentTop(competition.getWithCommentTop());
            competitionInDB.setRules(competition.getRules());

            competition = competitionRepository.save(competitionInDB);
        }


        if (competition.getImageCompetition() == null) {
            ImageCompetition imageCompetition = ImageCompetition.imageCompetition(null, competition.getReference(), competition);
            imageCompetitionRepository.save(imageCompetition);
        }

        return competition;
    }

    private boolean isAlreadyExist(Competition competitionInDB) {
        return competitionInDB != null;
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
