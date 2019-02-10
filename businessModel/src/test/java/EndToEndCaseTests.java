import com.lhc.business.BusinessConfig;
import com.lhc.business.dto.RankingCell;
import com.lhc.business.service.competition.*;
import com.lhc.datamodel.enumeration.RoleType;
import com.lhc.business.service.security.UserService;
import com.lhc.datamodel.entities.competition.Ballot;
import com.lhc.datamodel.entities.competition.Competition;
import com.lhc.datamodel.entities.competition.Match;
import com.lhc.datamodel.entities.competition.Vote;
import com.lhc.datamodel.entities.security.Role;
import com.lhc.datamodel.entities.security.User;
import com.lhc.datamodel.repository.security.RoleRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes =  BusinessConfig.class)
public class EndToEndCaseTests {

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
    @Autowired
    private RankingService rankingService;
    @Autowired
    private RoleRepository roleRepository;


    @Before
    public void config(){


    }

    public void createRole() throws ParseException {
        Role role1 = new Role();
        role1.setName(RoleType.ROLE_ADMIN.name());
        role1.setId(RoleType.ROLE_ADMIN.getValue());

        Role role2 = new Role();
        role2.setName(RoleType.ROLE_USER.name());
        role2.setId(RoleType.ROLE_USER.getValue());

        roleRepository.save(role1);
        roleRepository.save(role2);


    }

    public void saveUser(String username) throws ParseException {


        User user = new User();
        user.setUsername(username);
        user.setPassword("AAAA");
        user.setPasswordConfirm("AAAA");

        userService.save(user);

    }

    public void saveUser(String username, String password) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPasswordConfirm(password);

        userService.save(user);
    }



    public void saveCompetition(String nameCompetition) throws NoSuchAlgorithmException {

        String creatorUsername = "AAAA";

        Competition competition = Competition.competition();
        competition.setName(nameCompetition);
        competition.setPassword("AAAA");
        competition.setConfirmedPassword("AAAA");

        competition.setCreatorUsername(creatorUsername);
        competition.setDivision("D1");
        competition.setSeason("Z018-2019");
        competition.getMatches().add(Match.match());
        User user = userService.findByUsername(creatorUsername);

        competitionService.saveOrUpdate(competition, user );

    }



    public void addUserToCompetition(String username) throws NoSuchAlgorithmException, ParseException {

        User user = userService.findByUsername(username);

        Competition competition = competitionService.addUser(
                user,
                "Linkebeek","AAAA", true);

    }

    public String addMatch(String comp){

        Competition competition = competitionService.findByReference(comp);

        Assert.assertEquals(competition.getName(),comp);

        Match match = Match.match();
        match.setHomeTeam("Linkebeek");
        match.setAwayTeam("WaterDucks");
        match.setHomeScore(3);
        match.setAwayScore(3);
        match.setCompetition(competition);

        Match matchRecord = matchService.saveOrUpdate(match);

        return matchRecord.getReference();

    }

    public void userIsVoting(String username, String match_ref){

        User user = userService.findByUsername(username);

        Vote vote_1 = Vote.vote();
        vote_1.setName("AAAA");
        vote_1.setPoints(1);
        Vote vote_2 = Vote.vote();
        vote_2.setName("BBBB");
        vote_2.setPoints(2);
        Vote vote_3 = Vote.vote();
        vote_3.setName("CCCC");
        vote_3.setPoints(3);
        Vote vote_4 = Vote.vote();
        vote_4.setName("DDDD");
        vote_4.setPoints(4);

        Ballot ballot = Ballot.ballot();
        List<Vote> votes = new ArrayList<>();
        votes.add(vote_1);
        votes.add(vote_2);
        votes.add(vote_3);
        votes.add(vote_4);
        ballot.setVotes(votes);

        ballot.setUser(user);
        ballot.setMatch_ref(match_ref);

        ballotService.saveOrUpdate(ballot);


    }

    @Test
    public void createRoleTest() throws ParseException {

        createRole();
    }

    @Test
    @Ignore
    public void globalTest() throws NoSuchAlgorithmException, ParseException{

        createRole();
        saveUser("AAAA");
        saveUser("CCCC");
        saveUser("DDDD");
        saveUser("EEEE");
        saveCompetition("Linkebeek");
        addUserToCompetition("AAAA");
        addUserToCompetition("EEEE");
        addUserToCompetition("CCCC");
        addUserToCompetition("DDDD");
        String ref = addMatch("Linkebeek");
        saveUser("BBBB");
        addUserToCompetition("BBBB");
        userIsVoting("AAAA", ref);
        userIsVoting("BBBB", ref);
        userIsVoting("CCCC", ref);
        userIsVoting("DDDD", ref);

        List<Vote> votes = voteService.findAllByMatchReference(ref);


        List<RankingCell> ranking = rankingService.createTopByListVote(votes);


    }

    @Test
    @Ignore
    public void findAllByUsername_competitionsTest() {

        List<Competition> competitions = competitionService.findAllByUsername("Gauthier");
        System.out.println(competitions.toString());
    }
}
