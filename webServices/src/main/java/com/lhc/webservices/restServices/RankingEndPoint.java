package com.lhc.webservices.restServices;

import com.lhc.business.service.MatchService;
import com.lhc.business.service.RankingService;
import com.lhc.business.service.VoteService;
import com.lhc.datamodel.entities.Match;
import com.lhc.datamodel.entities.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

@Produces(MediaType.APPLICATION_JSON)
@RestController
public class RankingEndPoint {

    @Autowired
    private RankingService rankingService;
    @Autowired
    private VoteService voteService;
    @Autowired
    private MatchService matchService;


    @RequestMapping(
            value = "/ranking",
            method = RequestMethod.GET)
    public Map<String, Integer> getRanking(@RequestParam(value = "match_ref") String match_ref){

        List<Vote> votes = voteService.findAllByMatchReference(match_ref);

        Map<String, Integer> topFlop =  rankingService.createTopFlopByListVote(votes);

        return topFlop;

    }

}
