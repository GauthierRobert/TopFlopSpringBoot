package com.lhc.business.service.competition;

import com.lhc.datamodel.entities.competition.Competition;
import com.lhc.datamodel.entities.security.User;
import org.springframework.data.repository.query.Param;

import java.security.NoSuchAlgorithmException;
import java.util.List;


public interface CompetitionService {

    boolean alreadyExist(String ref);

    Competition saveOrUpdate(Competition competition, User user) throws NoSuchAlgorithmException;

    Competition addUser(User user, String postedName, String postedPassword, boolean isPlayer) throws NoSuchAlgorithmException;

    Competition findByReference(String ref);

    Competition findByName(String name);

    List<String> findUsersByCompetition(String ref);

    List<Competition> findAllByUsername(String username);

    Long deleteUserFromCompetition(String username, String competition_ref )

    Long deleteCompetition(String competition_ref);


}
