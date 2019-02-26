package com.lhc.webservices.restServices.impl;

import com.lhc.business.dto.RankingCell;
import com.lhc.business.service.competition.MatchService;
import com.lhc.business.service.competition.RankingService;
import com.lhc.business.service.competition.VoteService;
import com.lhc.datamodel.entities.competition.Vote;
import com.lhc.dto.Rankings;
import com.lhc.webservices.restServices.RankingEndPoint;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RankingEndPointImpl implements RankingEndPoint {

    @Autowired
    private RankingService rankingService;
    @Autowired
    private VoteService voteService;
    @Autowired
    private MatchService matchService;


    @Override
    @Deprecated
    public List<RankingCell> getRankingTop(@RequestParam(value = "match_ref") String match_ref){

        List<Vote> votes = voteService.findAllByMatchReference(match_ref);

        List<RankingCell> top =  rankingService.createTopByListVote(votes);

        return top;
    }

    @Override
    @Deprecated
    public List<RankingCell> getRankingFlop(@RequestParam(value = "match_ref") String match_ref){

        List<Vote> votes = voteService.findAllByMatchReference(match_ref);

        List<RankingCell> flop =  rankingService.createFlopByListVote(votes);

        return flop;

    }

    @Override
    public Rankings getRankings(String match_ref) {
        List<Vote> votes = voteService.findAllByMatchReference(match_ref);

        List<RankingCell> top =  rankingService.createTopByListVote(votes);
        List<RankingCell> flop =  rankingService.createFlopByListVote(votes);

        return new Rankings(top, flop);

    }

    @Override
    public Rankings getIntermediateRankings(String match_ref) {
        List<Vote> votes = voteService.findAllCountedByMatchReference(match_ref);

        List<RankingCell> top =  rankingService.createTopByListVote(votes);
        List<RankingCell> flop =  rankingService.createFlopByListVote(votes);

        return new Rankings(top, flop);

    }


}
