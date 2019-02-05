package com.lhc.business.service.competition.impl;

import com.lhc.business.service.competition.VoteService;
import com.lhc.datamodel.entities.competition.Vote;
import com.lhc.datamodel.entities.rules.Rule;
import com.lhc.datamodel.repository.competition.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VoteServiceImpl implements VoteService {

    private VoteRepository voteRepository;

    @Autowired
    public VoteServiceImpl(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Override
    public Vote saveOrUpdate(Vote vote, String competition_ref) {

        String ref;
        if (vote.getReference() !=null) {
            vote = voteRepository.findByReference(vote.getReference());
        } else {
            ref = UUID.randomUUID().toString();
            vote.setReference(ref);
        }

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
