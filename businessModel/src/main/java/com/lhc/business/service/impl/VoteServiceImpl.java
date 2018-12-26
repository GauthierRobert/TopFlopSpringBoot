package com.lhc.business.service.impl;

import com.lhc.business.service.VoteService;
import com.lhc.datamodel.entities.Vote;
import com.lhc.datamodel.entities.rules.Rule;
import com.lhc.datamodel.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

        Rule rule = getRuleWithVoteIndex(new ArrayList<>(), vote.getIndication());
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

        return rules.stream().filter(rule -> rule.getIndication().equals(index))
                .findFirst().orElse(null);
    }

}
