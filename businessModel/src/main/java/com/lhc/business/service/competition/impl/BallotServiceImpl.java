package com.lhc.business.service.competition.impl;

import com.lhc.business.service.competition.BallotService;
import com.lhc.business.service.competition.MatchService;
import com.lhc.datamodel.entities.competition.Ballot;
import com.lhc.datamodel.entities.competition.Match;
import com.lhc.datamodel.entities.security.User;
import com.lhc.datamodel.repository.competition.BallotRepository;
import com.lhc.datamodel.repository.competition.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BallotServiceImpl implements BallotService {

    private BallotRepository ballotRepository;

    private MatchRepository matchRepository;

    @Autowired
    public BallotServiceImpl(BallotRepository ballotRepository, MatchRepository matchRepository) {
        this.ballotRepository = ballotRepository;
        this.matchRepository = matchRepository;
    }

    @Override
    public List<Ballot> findAllBallotsByMatchReference(String match_ref) {

        return ballotRepository.findAllByMatchReference(match_ref);

    }

    @Override
    public Ballot saveOrUpdate(Ballot ballot) {
        Match match = matchRepository.findByReference(ballot.getMatch_ref());
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
