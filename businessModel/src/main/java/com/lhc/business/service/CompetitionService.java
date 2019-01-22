package com.lhc.business.service;

import com.lhc.datamodel.entities.Competition;
import com.lhc.datamodel.entities.security.User;

import java.security.NoSuchAlgorithmException;
import java.util.List;


public interface CompetitionService {

    Competition createCompetition(Competition competition, User user) throws NoSuchAlgorithmException;

    Competition addUserToCompetition(User user, String postedName, String postedPassword) throws NoSuchAlgorithmException;

    List<Competition> findAllByUser(User user);

    List<Competition> findAllByUsername(String username);

    Competition findByReference(String ref);

    List<String> findUsersbyCompetition(String ref);


}
