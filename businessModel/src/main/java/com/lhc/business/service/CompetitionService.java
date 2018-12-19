package com.lhc.business.service;

import com.lhc.business.dto.Competition;
import com.lhc.datamodel.entities.CompetitionRecord;
import com.lhc.datamodel.entities.security.User;

import java.security.NoSuchAlgorithmException;
import java.util.List;


public interface CompetitionService {

    CompetitionRecord createCompetition(Competition competition, User user) throws NoSuchAlgorithmException;

    List<Competition> findAllByUser(User user);

    List<Competition> findAllByUsername(String username);

    CompetitionRecord findByReference(String ref);

    CompetitionRecord addUserToCompetition(User user, String postedName, String postedPassword) throws NoSuchAlgorithmException;
}
