package com.lhc.business.service.competition;

import com.lhc.datamodel.entities.competition.Ballot;
import com.lhc.datamodel.entities.security.User;

import java.util.List;

public interface BallotService {

    List<Ballot> findAllBallotsByMatchReference(String match_ref);

    Ballot saveOrUpdate(Ballot ballot);
}
