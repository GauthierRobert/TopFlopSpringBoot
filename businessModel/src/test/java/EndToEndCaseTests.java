import com.lhc.business.BusinessConfig;
import com.lhc.business.enumeration.RoleType;
import com.lhc.business.service.*;
import com.lhc.business.service.security.UserService;
import com.lhc.datamodel.entities.Ballot;
import com.lhc.datamodel.entities.Competition;
import com.lhc.datamodel.entities.Match;
import com.lhc.datamodel.entities.Vote;
import com.lhc.datamodel.entities.security.Role;
import com.lhc.datamodel.entities.security.User;
import com.lhc.datamodel.repository.Security.RoleRepository;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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


    public void saveCompetition(String name) throws NoSuchAlgorithmException {
        Competition competition = Competition.competition();
        competition.setName(name);
        competition.setPassword("AAAA");
        competition.setConfirmedPassword("AAAA");
        competition.getMatches().add(Match.match());
        User user = userService.findByUsername("AAAA");

        competitionService.createCompetition(competition, user );


    }



    public void addUserToCompetition(String username) throws NoSuchAlgorithmException, ParseException {

        User user = userService.findByUsername(username);

        Competition competition = competitionService.addUserToCompetition(
                user,
                "Linkebeek","AAAA");

    }

    public String addMatch(String comp){

        Competition competition = competitionService.findByReference(comp);

        Assert.assertEquals(competition.getName(),comp);

        Match match = new Match();
        match.setHomeTeam("Linkebeek");
        match.setAwayTeam("WaterDucks");
        match.setHomeScore(3);
        match.setAwayScore(3);

        Match matchRecord = matchService.saveOrUpdate(match, comp);

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

        Ballot ballot = Ballot.ballot()
        List<Vote> votes = new ArrayList<>();
        votes.add(vote_1);
        votes.add(vote_2);
        votes.add(vote_3);
        votes.add(vote_4);
        ballot.setVotes(votes);

        ballotService.saveOrUpdate(ballot, user, match_ref);


    }

    @Test
    public void name() throws ParseException {

        createRole();
    }

    @Test
    public void globalTest() throws NoSuchAlgorithmException, ParseException{

        createRole();
        saveUser("AAAA" + Math.random());
        saveUser("CCCC" + Math.random());
        saveUser("DDDD" + Math.random());
        saveUser("EEEE" + Math.random());
        saveCompetition("Linkebeek");
        addUserToCompetition("AAAA");
        addUserToCompetition("EEEE");
        addUserToCompetition("CCCC");
        addUserToCompetition("DDDD");
        String ref = addMatch("Linkebeek");
        saveUser("BBBB" + Math.random());
        addUserToCompetition("BBBB");
        userIsVoting("AAAA", ref);
        userIsVoting("BBBB", ref);
        userIsVoting("CCCC", ref);
        userIsVoting("DDDD", ref);

        List<Vote> votes = voteService.findAllByMatchReference(ref);


        Map<String, Integer> ranking = rankingService.createTopFlopByListVote(votes);

        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<String, Integer>> iter = ranking.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Integer> entry = iter.next();
            sb.append(entry.getKey());
            sb.append('=').append('"');
            sb.append(entry.getValue());
            sb.append('"');
            if (iter.hasNext()) {
                sb.append(',').append(' ');
            }
        }

        System.out.println(sb.toString());
    }

    @Test
    public void name2() {

        List<Competition> competitions = competitionService.findAllByUsername("AAAA");
        System.out.println(competitions.toString());
    }
}
