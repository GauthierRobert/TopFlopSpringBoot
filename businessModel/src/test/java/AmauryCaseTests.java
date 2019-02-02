import com.lhc.business.BusinessConfig;
import com.lhc.business.dto.RankingCell;
import com.lhc.business.service.*;
import com.lhc.business.service.security.UserService;
import com.lhc.datamodel.entities.Ballot;
import com.lhc.datamodel.entities.Competition;
import com.lhc.datamodel.entities.Match;
import com.lhc.datamodel.entities.Vote;
import com.lhc.datamodel.entities.security.Role;
import com.lhc.datamodel.entities.security.User;
import com.lhc.datamodel.enumeration.RoleType;
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
import java.util.List;

import static com.lhc.datamodel.builder.CompetitionBuilder.aCompetition;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes =  BusinessConfig.class)
public class AmauryCaseTests {

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

    public void saveUser(String username, String password) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPasswordConfirm(password);

        userService.save(user);
    }


    public void saveCompetition() throws NoSuchAlgorithmException {

        User user = userService.findByUsername(CREATOR);

        competitionService.createCompetition(competition_linkebeek, user );

    }



    public void addUserToCompetition(String username, String competitionName, String competitionPassword) throws NoSuchAlgorithmException, ParseException {

        User user = userService.findByUsername(username);

       competitionService.addUserToCompetition(
                user,
                competitionName,
                competitionPassword);

    }

    private static String CREATOR = "Ton_Username";
    private static String CREATOR_PASSWORD = "Ton_password";
    //Les passwords peuvent être temporaire. Je rajouterai un changement de password

    private static String USERNAME_1 = "";
    private static String PASSWORD_1 = "";

    private static String USERNAME_2 = "";
    private static String PASSWORD_2 = "";

    // ...

    private static String NAME_COMPETITION = "";
    private static String PASSWORD_COMPETITION = "";
    private static String DIVISION = "";
    private static String SEASON = "";
    private static boolean COMMENTAIRE_TOP = false;
    private static boolean COMMENTAIRE_FLOP = true;

    private static Competition competition_linkebeek = aCompetition()
            .withName(NAME_COMPETITION)
            .withDivision(DIVISION)
            .withSeason(SEASON)
            .withCreatorUsername(CREATOR)
            .withPassword(PASSWORD_COMPETITION)
            .withConfirmedPassword(PASSWORD_COMPETITION)
            .withComments(COMMENTAIRE_TOP, COMMENTAIRE_FLOP)
            .withRules(4,2)
            .withTopRules(4,3,2,1)
            .build();

    @Test
    @Ignore
    public void globalTest() throws NoSuchAlgorithmException, ParseException{

        createRole();

        saveUser(USERNAME_1, PASSWORD_1);
        saveUser(USERNAME_2, PASSWORD_2);

        saveCompetition();

        addUserToCompetition(USERNAME_1, NAME_COMPETITION, PASSWORD_COMPETITION);
        addUserToCompetition(USERNAME_2, NAME_COMPETITION, PASSWORD_COMPETITION);

    }
}
