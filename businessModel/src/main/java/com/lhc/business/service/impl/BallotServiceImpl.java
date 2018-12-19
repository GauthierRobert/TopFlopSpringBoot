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

    private MapperHandler mapperHandler;

    private BallotRepository ballotRepository;

    private MatchService matchService;

    @Autowired
    public BallotServiceImpl(MapperHandler mapperHandler, BallotRepository ballotRepository, MatchService matchService) {
        this.mapperHandler = mapperHandler;
        this.ballotRepository = ballotRepository;
        this.matchService = matchService;
    }

    @Override
    public List<Ballot> findAllBallotsByMatchReference(String match_ref) {

        List<BallotRecord> ballotRecords = ballotRepository.findAllByMatchReference(match_ref);
        List<Ballot> ballots = new ArrayList<>();
        ballotRecords.forEach(ballotRecord -> {
            Ballot ballot = new Ballot();
            ballot = mapperHandler.mapBallot(ballotRecord, ballot);
            ballots.add(ballot);
        });

        return ballots;

    }

    @Override
    public BallotRecord saveOrUpdate(Ballot ballot, User user, String match_ref) {
        MatchRecord matchRecord = matchService.findMatchByReference(match_ref);
        String ref;
        BallotRecord ballotRecord = null;
        if (ballot.getReference() !=null) {
            ballotRecord = ballotRepository.findByReference(ballot.getReference());
            ref = ballot.getReference();
        } else {
            ref = UUID.randomUUID().toString();
            ballot.setReference(ref);
        }



        final BallotRecord br = mapperHandler.mapBallotRecord(ballot, ballotRecord);

        List<VoteRecord> voteRecords = new ArrayList<>();
        final String ref_f = ref;
        final AtomicInteger i = new AtomicInteger(0);
        ballot.getVotes().forEach(vote -> {
            i.addAndGet(1);
            VoteRecord voteRecord = new VoteRecord();
            mapperHandler.mapVoteRecord(vote, voteRecord);
            voteRecord.setBallotRecord(br);
            voteRecord.setReference(ref_f +"_"+ i.toString());
            voteRecords.add(voteRecord);
        });

        br.setMatchRecord(matchRecord);
        br.setUser(user);
        br.setVotes(voteRecords);


        return ballotRepository.save(br);



    }

}
