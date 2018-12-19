package com.lhc.webservices.restServices;

import com.lhc.business.dto.Ballot;
import com.lhc.business.dto.Competition;
import com.lhc.business.dto.Match;
import com.lhc.business.dto.Vote;
import com.lhc.business.service.BallotService;
import com.lhc.business.service.CompetitionService;
import com.lhc.business.service.MatchService;
import com.lhc.business.service.VoteService;
import com.lhc.business.service.security.UserService;
import com.lhc.datamodel.entities.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@RestController
public class CompetitionEndPoint {

    @Autowired
    private BallotService ballotService;
    @Autowired
    private CompetitionService competitionService;
    @Autowired
    private MatchService matchService;
    @Autowired
    private VoteService voteService;
    @Autowired
    private UserService userService;


    //--------- POST
    @RequestMapping(
            value = "/ballot",
            method = RequestMethod.POST)
    public void postBallot(@RequestBody Ballot ballot, @RequestParam String match_ref){

        org.springframework.security.core.userdetails.User user =
                (org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User currentUser = userService.findByUsername(user.getUsername());

        ballotService.saveOrUpdate(ballot, currentUser, match_ref);
    }

    @RequestMapping(
            value = "/competition",
            method = RequestMethod.POST)
    public void postCompetition(@RequestBody Competition competition) throws NoSuchAlgorithmException {

        org.springframework.security.core.userdetails.User user =
                (org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User currentUser = userService.findByUsername(user.getUsername());

        competitionService.createCompetition(competition, currentUser);
    }

    @RequestMapping(
            value = "/match",
            method = RequestMethod.POST)
    public void postMatch(@RequestBody Match match){

        matchService.saveOrUpdate(match, "Linkebeek");

    }


    //----------- GET
    @RequestMapping(
            value = "/match",
            method = RequestMethod.GET)
    public List<Match> getMatchesWithCompetitionRef(@RequestParam(value = "competition_ref") String competition_ref){

        return matchService.findAllMatchesByCompetitionReference(competition_ref);

    }


    @RequestMapping(
            value = "/ballot",
            method = RequestMethod.GET)
    public List<Ballot> getBallotsWithMatchRef(@RequestParam(value = "match_ref") String match_ref){

        return ballotService.findAllBallotsByMatchReference(match_ref);

    }

    @RequestMapping(
            value = "/vote",
            method = RequestMethod.GET)
    public List<Vote> getVotesWithBallotRef(@RequestParam(value = "ballot_ref") String ballot_ref){

        return voteService.findAllByBallotReference(ballot_ref);

    }

    @RequestMapping(
            value = "/competition",
            method = RequestMethod.GET)
    public List<Competition> getCompetitionLinkToUser(@RequestBody User user){

         return competitionService.findAllByUser(user);

    }




}
