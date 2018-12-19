package com.lhc.business.service;

import com.lhc.business.dto.Ballot;
import com.lhc.datamodel.entities.BallotRecord;
import com.lhc.datamodel.entities.security.User;

import java.util.List;

public interface BallotService {

    List<Ballot> findAllBallotsByMatchReference(String match_ref);

    BallotRecord saveOrUpdate(Ballot ballot, User user, String match_ref);

}
