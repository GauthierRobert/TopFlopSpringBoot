package com.lhc.business.service.impl;

import com.lhc.business.dto.Vote;
import com.lhc.business.service.VoteService;
import com.lhc.business.service.mapper.MapperHandler;
import com.lhc.datamodel.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.lhc.business.dto.Competition.getCompetitionRules;

@Service
public class VoteServiceImpl implements VoteService {

    private VoteRepository voteRepository;

    @Autowired
    public VoteServiceImpl(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Override
    public Vote saveOrUpdate(Vote vote, String competition_ref) {

        if (vote.getReference() !=null) {
            vote = voteRepository.findByReference(vote.getReference());
        }

        Rule rule=  getRuleWithVoteIndex(new ArrayList<Rule>(), vote.getIndex());
        vote.applyRule(rule);

        return voteRepository.save(vote);
    }

    @Override
    public List<Vote> findAllByMatchReference(String ref) {

        return  voteRepository.findAllByMatchReference(ref);
    }

    @Override
    public List<Vote> findAllByBallotReference(String ref) {

        return voteRepository.findAllByBallotReference(ref);
    }


    private Rule getRuleWithVoteIndex(List<Rule> rules, int index){

        rules.forEach(rule -> {
            if (index == rule.getIndex()){
                return rule;
            }
        });

        return null;
    }

}
