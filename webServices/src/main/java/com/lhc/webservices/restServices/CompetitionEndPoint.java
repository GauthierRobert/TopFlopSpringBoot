package com.lhc.webservices.restServices;

import com.lhc.business.service.BallotService;
import com.lhc.business.service.CompetitionService;
import com.lhc.business.service.MatchService;
import com.lhc.business.service.VoteService;
import com.lhc.business.service.mapper.competition.CompetitionMapperHandler;
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
    public void postBallot(@RequestBody BallotDto ballotDto, @RequestParam String match_ref){

        org.springframework.security.core.userdetails.User user =
                (org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User currentUser = userService.findByUsername(user.getUsername());

        BallotMapperHandler ballotMapperHandler = new BallotMapperHandler();
        Ballot ballot = ballotMapperHandler.mapToEntity(ballotDto, new Ballot);


        ballotService.saveOrUpdate(ballot, currentUser, match_ref);
    }

    @RequestMapping(
            value = "/competition",
            method = RequestMethod.POST)
    public void postCompetition(@RequestBody CompetitionDto competitionDto) throws NoSuchAlgorithmException {

        org.springframework.security.core.userdetails.User user =
                (org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User currentUser = userService.findByUsername(user.getUsername());

        CompetitionMapperHandler competitionMapperHandler = new CompetitionMapperHandler();
        Competition competition = competitionMapperHandler.mapToEntity(competitionDto, new Competition());

        competitionService.createCompetition(competition, currentUser);
    }

    @RequestMapping(
            value = "/match",
            method = RequestMethod.POST)
    public void postMatch(@RequestBody MatchDto matchDto){

        MatchMapperHandler matchMapperHandler = new MatchMapperHandler();
        Match match = matchMapperHandler.mapToEntity(matchDto, new Match());

        matchService.saveOrUpdate(match, "Linkebeek");

    }


    //----------- GET
    @RequestMapping(
            value = "/match",
            method = RequestMethod.GET)
    public List<MatchDto> getMatchesWithCompetitionRef(@RequestParam(value = "competition_ref") String competition_ref){

        List<Match> matches = matchService.findAllMatchesByCompetitionReference(competition_ref);

        MatchMapperHandler matchMapperHandler = new MatchMapperHandler();
        return matchMapperHandler.mapToDtos(matches);

    }


    @RequestMapping(
            value = "/ballot",
            method = RequestMethod.GET)
    public List<BallotDto> getBallotsWithMatchRef(@RequestParam(value = "match_ref") String match_ref){

        List<Ballot> ballots = ballotService.findAllBallotsByMatchReference(match_ref);

        BallotMapperHandler ballotMapperHandler = new BallotMapperHandler();
        
        return ballotMapperHandler.mapToDtos(ballots);

    }

    @RequestMapping(
            value = "/vote",
            method = RequestMethod.GET)
    public List<VoteDto> getVotesWithBallotRef(@RequestParam(value = "ballot_ref") String ballot_ref){

        List<Vote> votes = voteService.findAllByBallotReference(ballot_ref);
        VoteMapperHandler voteMapperHandler = new VoteMapperHandler();
        
        return voteMapperHandler.mapToDtos(votes);

    }

    @RequestMapping(
            value = "/competition",
            method = RequestMethod.GET)
    public List<CompetitionDto> getCompetitionLinkToUser(@RequestBody String username){

        User user = userService.findByUsername(username);
        List<Competition> competitions = competitionService.findAllByUser(user);
        CompetitionMapperHandler competitionMapperHandler = new CompetitionMapperHandler();
        
        return competitionMapperHandler.mapToEntities(competitions);

    }




}
