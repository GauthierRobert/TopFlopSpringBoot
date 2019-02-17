package com.lhc.business.service.competition;

import com.lhc.datamodel.entities.competition.Competition;
import com.lhc.datamodel.entities.security.User;

import java.security.NoSuchAlgorithmException;
import java.util.List;


public interface CompetitionService {

    boolean alreadyExist(String ref);

    Competition saveOrUpdate(Competition competition) throws NoSuchAlgorithmException;

    Competition addUser(User user, String postedName, String postedPassword, boolean isPlayer) throws NoSuchAlgorithmException;

    Competition findByReference(String ref);

    Competition findByName(String name);

    List<String> findUsersByCompetition(String ref);

    List<Competition> findAllByUsername(String username);

    void deleteUserFromCompetition(String username, String competition_ref);

    void deleteCompetition(String competition_ref);


}
