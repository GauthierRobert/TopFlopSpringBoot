package com.lhc.webservices.restServices.impl;

import com.lhc.business.dto.RankingCell;
import com.lhc.business.service.MatchService;
import com.lhc.business.service.RankingService;
import com.lhc.business.service.VoteService;
import com.lhc.datamodel.entities.Vote;
import com.lhc.webservices.restServices.RankingEndPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public class RankingEndPointImpl implements RankingEndPoint {

    @Autowired
    private RankingService rankingService;
    @Autowired
    private VoteService voteService;
    @Autowired
    private MatchService matchService;


    @Override
    public List<RankingCell> getRankingTop(@RequestParam(value = "match_ref") String match_ref){

        List<Vote> votes = voteService.findAllByMatchReference(match_ref);

        List<RankingCell> top =  rankingService.createTopByListVote(votes);

        return top;
    }

    @Override
    public List<RankingCell> getRankingFlop(@RequestParam(value = "match_ref") String match_ref){

        List<Vote> votes = voteService.findAllByMatchReference(match_ref);

        List<RankingCell> flop =  rankingService.createFlopByListVote(votes);

        return flop;

    }

}
