import com.lhc.business.BusinessConfig;
import com.lhc.business.service.competition.*;
import com.lhc.business.service.security.UserService;
import com.lhc.datamodel.entities.competition.Competition;
import com.lhc.datamodel.entities.security.Role;
import com.lhc.datamodel.entities.security.User;
import com.lhc.datamodel.enumeration.RoleType;
import com.lhc.datamodel.repository.security.RoleRepository;
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

import static com.lhc.datamodel.builder.CompetitionBuilder.aCompetition;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes =  BusinessConfig.class)
public class RemontadaCaseTests {

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

        competitionService.saveOrUpdate(competitionLinkebeek, user );

    }



    public void addPlayerToCompetition(String username, String competitionName, String competitionPassword) throws NoSuchAlgorithmException, ParseException {

        User user = userService.findByUsername(username);

       competitionService.addUser(
                user,
                competitionName,
                competitionPassword, true);

    }

    private static String CREATOR = "Gauthier";
    private static String CREATOR_PASSWORD = "gauthier";

    private static String USERNAME_1 = "Nicolas";
    private static String PASSWORD_1 = "nicolas";

    private static String USERNAME_2 = "Percy";
    private static String PASSWORD_2 = "percy";

    private static String USERNAME_3 = "Coco";
    private static String PASSWORD_3 = "coco";

    private static String USERNAME_4 = "Tristan";
    private static String PASSWORD_4 = "tristan";

    private static String USERNAME_5 = "Morti";
    private static String PASSWORD_5 = "morti";

    private static String USERNAME_6 = "JP";
    private static String PASSWORD_6 = "jp";

    private static String USERNAME_7 = "Gilles";
    private static String PASSWORD_7 = "gilles";

    private static String USERNAME_8 = "Antoine";
    private static String PASSWORD_8 = "antoine";

    private static String USERNAME_9 = "Thamos";
    private static String PASSWORD_9 = "thamos";

    private static String USERNAME_10 = "Impala";
    private static String PASSWORD_10 = "impala";

    // ...

    private static String NAME_COMPETITION = "Remontada";
    private static String PASSWORD_COMPETITION = "remontada";
    private static String DIVISION = "P4";
    private static String SEASON = "2018-2019";
    private static String TOP_NAME = "Top";
    private static String FLOP_NAME = "Flop";
    private static boolean COMMENTAIRE_TOP = true;
    private static boolean COMMENTAIRE_FLOP = true;

    private static Competition competitionLinkebeek = aCompetition()
            .withName(NAME_COMPETITION)
            .withDivision(DIVISION)
            .withSeason(SEASON)
            .withCreatorUsername(CREATOR)
            .withPassword(PASSWORD_COMPETITION)
            .withConfirmedPassword(PASSWORD_COMPETITION)
            .withTopFlopName(TOP_NAME, FLOP_NAME)
            .withComments(COMMENTAIRE_TOP, COMMENTAIRE_FLOP)
            .withRules(1,1)
            .withTopRules(1)
            .withFlopRules(1)
            .build();

    @Test
    @Ignore
    public void globalTest() throws NoSuchAlgorithmException, ParseException{

        createRole();

        saveUser(CREATOR, CREATOR_PASSWORD);
        saveUser(USERNAME_1, PASSWORD_1);
        saveUser(USERNAME_2, PASSWORD_2);
        saveUser(USERNAME_3, PASSWORD_3);
        saveUser(USERNAME_4, PASSWORD_4);
        saveUser(USERNAME_5, PASSWORD_5);
        saveUser(USERNAME_6, PASSWORD_6);
        saveUser(USERNAME_7, PASSWORD_7);
        saveUser(USERNAME_8, PASSWORD_8);
        saveUser(USERNAME_9, PASSWORD_9);
        saveUser(USERNAME_10, PASSWORD_10);

        saveCompetition();

        addPlayerToCompetition(USERNAME_1, NAME_COMPETITION, PASSWORD_COMPETITION);
        addPlayerToCompetition(USERNAME_2, NAME_COMPETITION, PASSWORD_COMPETITION);
        addPlayerToCompetition(USERNAME_3, NAME_COMPETITION, PASSWORD_COMPETITION);
        addPlayerToCompetition(USERNAME_4, NAME_COMPETITION, PASSWORD_COMPETITION);
        addPlayerToCompetition(USERNAME_5, NAME_COMPETITION, PASSWORD_COMPETITION);
        addPlayerToCompetition(USERNAME_6, NAME_COMPETITION, PASSWORD_COMPETITION);
        addPlayerToCompetition(USERNAME_7, NAME_COMPETITION, PASSWORD_COMPETITION);
        addPlayerToCompetition(USERNAME_8, NAME_COMPETITION, PASSWORD_COMPETITION);
        addPlayerToCompetition(USERNAME_9, NAME_COMPETITION, PASSWORD_COMPETITION);
        addPlayerToCompetition(USERNAME_10, NAME_COMPETITION, PASSWORD_COMPETITION);

    }
}
