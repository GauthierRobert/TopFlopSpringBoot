package com.lhc.webservices.restServices;

import com.lhc.business.dto.Vote;
import com.lhc.business.service.RankingService;
import com.lhc.business.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Produces(MediaType.APPLICATION_JSON)
@ApplicationPath("/final")
@RestController
public class RankingEndPoint {

    @Autowired
    private RankingService rankingService;
    @Autowired
    private VoteService voteService;

    @RolesAllowed("USER_ADMIN")
    @RequestMapping(
            value = "/ranking",
            method = RequestMethod.GET)
    public Response getRanking(@RequestParam(value = "match_ref") String match_ref){

        List<Vote> votes = voteService.findAllByMatchReference(match_ref);

        Map<String, Integer> topFlop =  rankingService.createTopFlopByListVote(votes);

        return Response.status(200).entity(topFlop).build();

    }

}