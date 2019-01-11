package com.lhc.webservices.restServices;

import com.lhc.business.service.BallotService;
import com.lhc.business.service.CompetitionService;
import com.lhc.business.service.MatchService;
import com.lhc.business.service.VoteService;
import com.lhc.business.service.security.UserService;
import com.lhc.datamodel.entities.Ballot;
import com.lhc.datamodel.entities.Competition;
import com.lhc.datamodel.entities.Match;
import com.lhc.datamodel.entities.Vote;
import com.lhc.datamodel.entities.security.User;
import com.lhc.dto.BallotDto;
import com.lhc.dto.CompetitionDto;
import com.lhc.dto.MatchDto;
import com.lhc.dto.VoteDto;
import com.lhc.mapper.MapperHandler;
import com.lhc.mapper.ballot.BallotMapperHandler;
import com.lhc.mapper.competition.CompetitionMapperHandler;
import com.lhc.mapper.match.MatchMapperHandler;
import com.lhc.mapper.vote.VoteMapperHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
            value = "/ballot/save",
            method = RequestMethod.POST)
    public BallotDto postBallot(@RequestBody BallotDto ballotDto, @RequestParam String username){

        User currentUser = userService.findByUsername(username);

        BallotMapperHandler ballotMapperHandler = new BallotMapperHandler();
        Ballot ballot = ballotMapperHandler.createEntityFromDTO(ballotDto);

        ballotService.saveOrUpdate(ballot, currentUser, ballot.getMatch_ref());

        return ballotDto;
    }

    @RequestMapping(
            value = "/competition/save",
            method = RequestMethod.POST)
    public CompetitionDto postCompetition(@RequestBody CompetitionDto competitionDto ) throws NoSuchAlgorithmException {

        User currentUser = userService.findByUsername(competitionDto.getCreatorUsername());

        CompetitionMapperHandler competitionMapperHandler = new CompetitionMapperHandler();

        if (competitionDto.getConfirmedPassword().equals(competitionDto.getPassword())) {
            Competition competition = competitionMapperHandler.createEntityFromDTO(competitionDto);
            competitionService.createCompetition(competition, currentUser);
            return competitionDto;
        } else {
            return null;
        }
    }

    @RequestMapping(
            value = "/match/save",
            method = RequestMethod.POST)
    public MatchDto postMatch(@RequestBody MatchDto matchDto){

        MatchMapperHandler matchMapperHandler = new MatchMapperHandler();
        Match match = matchMapperHandler.createEntityFromDTO(matchDto);

        matchService.saveOrUpdate(match, matchDto.getCompetition_ref());

        return matchDto;

    }

    @RequestMapping(
            value = "/competition/addUser",
            method = RequestMethod.POST)
    public CompetitionDto addUserToCompetition(@RequestParam(value = "competition_ref") String competition_ref,
                                               @RequestParam(value = "username") String username,
                                               @RequestParam(value = "password") String password) throws NoSuchAlgorithmException {

        User currentUser = userService.findByUsername(username);
        Competition competition = competitionService.addUserToCompetition(currentUser, competition_ref, password);

        CompetitionMapperHandler competitionMapperHandler = new CompetitionMapperHandler();
        CompetitionDto competitionDto = competitionMapperHandler.createDTOFromEntity(competition);
        return competitionDto;

    }

    //------------------------------------------- GET
    @RequestMapping(
            value = "/match/get",
            method = RequestMethod.GET)
    public List<MatchDto> getMatchesWithCompetitionRef(@RequestParam(value = "competition_ref") String competition_ref){

        List<Match> matches = matchService.findAllMatchesByCompetitionReference(competition_ref);

        MatchMapperHandler matchMapperHandler = new MatchMapperHandler();
        return matchMapperHandler.mapToListDtos(matches);

    }


    @RequestMapping(
            value = "/ballot/get",
            method = RequestMethod.GET)
    public List<BallotDto> getBallotsWithMatchRef(@RequestParam(value = "match_ref") String match_ref){

        List<Ballot> ballots = ballotService.findAllBallotsByMatchReference(match_ref);

        BallotMapperHandler ballotMapperHandler = new BallotMapperHandler();
        
        return ballotMapperHandler.mapToListDtos(ballots);

    }

    @RequestMapping(
            value = "/vote/get",
            method = RequestMethod.GET)
    public List<VoteDto> getVotesWithBallotRef(@RequestParam(value = "ballot_ref") String ballot_ref){

        List<Vote> votes = voteService.findAllByBallotReference(ballot_ref);
        VoteMapperHandler voteMapperHandler = new VoteMapperHandler();
        
        return voteMapperHandler.mapToListDtos(votes);

    }

    @RequestMapping(
            value = "/competition/get",
            method = RequestMethod.GET)
    public List<CompetitionDto> getCompetitionLinkToUser(@RequestParam(value = "username") String username){

        List<Competition> competitions = competitionService.findAllByUsername(username);
        CompetitionMapperHandler competitionMapperHandler = new CompetitionMapperHandler();
        
        return competitionMapperHandler.mapToListDtos(competitions);

    }
}
