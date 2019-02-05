package com.lhc.business.service.competition.impl;

import com.lhc.business.service.competition.BallotService;
import com.lhc.business.service.competition.MatchService;
import com.lhc.datamodel.entities.competition.Ballot;
import com.lhc.datamodel.entities.competition.Match;
import com.lhc.datamodel.entities.security.User;
import com.lhc.datamodel.repository.competition.BallotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
    public Ballot saveOrUpdate(Ballot ballot) {
        Match match = matchService.findMatchByReference(ballot.getMatch_ref());
        String reference;
        if (ballot.getReference() !=null) {
            ballot = ballotRepository.findByReference(ballot.getReference());
        } else {
            reference = UUID.randomUUID().toString();
            ballot.setReference(reference);
        }

        ballot.setMatch(match);

        return ballotRepository.save(ballot);

    }

}
