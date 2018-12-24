package com.lhc.business.service;

import com.lhc.business.dto.Vote;
import com.lhc.datamodel.entities.VoteRecord;

import java.util.List;


public interface VoteService {

    Vote saveOrUpdate(Vote vote);

    List<Vote> findAllByMatchReference(String ref);

    List<Vote> findAllByBallotReference(String ref);
}
