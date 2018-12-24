package com.lhc.business.service.impl;

import com.lhc.business.dto.Ballot;
import com.lhc.business.service.BallotService;
import com.lhc.business.service.MatchService;
import com.lhc.business.service.mapper.MapperHandler;
import com.lhc.datamodel.entities.BallotRecord;
import com.lhc.datamodel.entities.MatchRecord;
import com.lhc.datamodel.entities.VoteRecord;
import com.lhc.datamodel.entities.security.User;
import com.lhc.datamodel.repository.BallotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class BallotServiceImpl implements BallotService {

    private BallotRepository ballotRepository;

    private MatchService matchService;

    @Autowired
    public BallotServiceImpl(BallotRepository ballotRepository, MatchService matchService) {
        this.ballotRepository = ballotRepository;
        this.matchService = matchService;
    }

    @Override
    public List<Ballot> findAllBallotsByMatchReference(String match_ref) {

        return ballotRepository.findAllByMatchReference(match_ref);

    }

    @Override
    public Ballot saveOrUpdate(Ballot ballot, User user, String match_ref) {
        Match match = matchService.findMatchByReference(match_ref);
        String ref;
        if (ballot.getReference() !=null) {
            ballot = ballotRepository.findByReference(ballot.getReference());
            ref = ballot.getReference();
        } else {
            ref = UUID.randomUUID().toString();
            ballot.setReference(ref);
        }

        br.setMatchRecord(matchRecord);
        br.setUser(user);

        return ballotRepository.save(br);

    }

}
