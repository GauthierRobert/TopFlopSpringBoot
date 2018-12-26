package com.lhc.business.service;

import com.lhc.datamodel.entities.Vote;

import java.util.List;


public interface VoteService {

    Vote saveOrUpdate(Vote vote, String competition_ref);

    List<Vote> findAllByMatchReference(String ref);

    List<Vote> findAllByBallotReference(String ref);
}
