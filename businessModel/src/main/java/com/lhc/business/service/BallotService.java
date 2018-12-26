package com.lhc.business.service;

import com.lhc.datamodel.entities.Ballot;
import com.lhc.datamodel.entities.security.User;

import java.util.List;

public interface BallotService {

    List<Ballot> findAllBallotsByMatchReference(String match_ref);

    Ballot saveOrUpdate(Ballot ballot, User user, String match_ref);

}
