package com.lhc.business.service.impl;

import com.lhc.business.dto.Vote;
import com.lhc.business.service.VoteService;
import com.lhc.business.service.mapper.MapperHandler;
import com.lhc.datamodel.entities.VoteRecord;
import com.lhc.datamodel.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.lhc.business.dto.Competition.getCompetitionRules;

@Service
public class VoteServiceImpl implements VoteService {

    private VoteRepository voteRepository;

    private MapperHandler mapperHandler;



    @Autowired
    public VoteServiceImpl(VoteRepository voteRepository, MapperHandler mapperHandler) {
        this.voteRepository = voteRepository;
        this.mapperHandler = mapperHandler;
    }

    @Override
    public VoteRecord saveOrUpdate(Vote vote, String competition_ref) {

        VoteRecord voteRecord = null;
        if (vote.getReference() !=null) {
            voteRecord = voteRepository.findByReference(vote.getReference());
        }

        RuleRecord rule=  getRuleWithVoteIndex(new ArrayList<RuleRecord>(), vote.getIndex());
        vote.applyRule(rule);

        voteRecord = mapperHandler.mapVoteRecord(vote, voteRecord);


        return voteRepository.save(voteRecord);
    }

    @Override
    public List<Vote> findAllByMatchReference(String ref) {

        List<VoteRecord> voteRecords = voteRepository.findAllByMatchReference(ref);
        List<Vote> votes = new ArrayList<>();

        voteRecords.forEach(voteRecord -> {
            Vote vote = new Vote();
            vote = mapperHandler.mapVote(voteRecord, vote);
            votes.add(vote);
        });

        return votes;
    }

    @Override
    public List<Vote> findAllByBallotReference(String ref) {
        List<VoteRecord> voteRecords = voteRepository.findAllByBallotReference(ref);
        List<Vote> votes = new ArrayList<>();

        voteRecords.forEach(voteRecord -> {
            Vote vote = new Vote();
            vote = mapperHandler.mapVote(voteRecord, vote);
            votes.add(vote);
        });

        return votes;
    }


    private RuleRecord getRuleWithVoteIndex(List<RuleRecord> rules, int index){

        rules.forEach(rule -> {
            if (index == rule.getIndex()){
                return rule;
            }
        });

        return null;
    }

}
