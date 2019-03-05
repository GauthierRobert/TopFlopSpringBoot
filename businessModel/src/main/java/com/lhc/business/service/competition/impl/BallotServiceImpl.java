package com.lhc.business.service.competition.impl;

import com.lhc.business.service.competition.BallotService;
import com.lhc.datamodel.entities.competition.Ballot;
import com.lhc.datamodel.entities.competition.Match;
import com.lhc.datamodel.repository.competition.BallotRepository;
import com.lhc.datamodel.repository.competition.MatchRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        if (ballot.getReference() == null) {
            ballot.setReference(UUID.randomUUID().toString());
            Match match = matchRepository.findByReference(ballot.getMatch_ref());
            ballot.setMatch(match);
        }

        return ballotRepository.save(ballot);

    }

    @Override
    public Ballot findByReference(String ballot_ref) {
        return ballotRepository.findByReference(ballot_ref);
    }


}
