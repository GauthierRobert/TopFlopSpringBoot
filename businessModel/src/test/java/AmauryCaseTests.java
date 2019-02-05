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

        competitionService.saveOrUpdate(competition_linkebeek, user );

    }



    public void addUserToCompetition(String username, String competitionName, String competitionPassword) throws NoSuchAlgorithmException, ParseException {

        User user = userService.findByUsername(username);

       competitionService.addUser(
                user,
                competitionName,
                competitionPassword);

    }

    private static String CREATOR = "AmauryM";
    private static String CREATOR_PASSWORD = "amo";

    private static String USERNAME_1 = "Boige";
    private static String PASSWORD_1 = "boige";

    private static String USERNAME_2 = "Alex";
    private static String PASSWORD_2 = "alex";

    private static String USERNAME_3 = "Pacome";
    private static String PASSWORD_3 = "pacome";

    private static String USERNAME_4 = "Flahou";
    private static String PASSWORD_4 = "flahou";

    private static String USERNAME_5 = "Juane";
    private static String PASSWORD_5 = "juane";

    private static String USERNAME_6 = "Hoche";
    private static String PASSWORD_6 = "hoche";

    private static String USERNAME_7 = "Max";
    private static String PASSWORD_7 = "max";

    private static String USERNAME_8 = "Fab";
    private static String PASSWORD_8 = "fab";

    private static String USERNAME_9 = "Joa";
    private static String PASSWORD_9 = "joa";

    private static String USERNAME_10 = "Come";
    private static String PASSWORD_10 = "come";

    private static String USERNAME_11 = "Octa";
    private static String PASSWORD_11 = "octa";

    private static String USERNAME_12 = "Adri";
    private static String PASSWORD_12 = "adri";

    private static String USERNAME_13 = "MartVL";
    private static String PASSWORD_13 = "martvl";

    private static String USERNAME_14 = "Nato";
    private static String PASSWORD_14 = "nato";

    private static String USERNAME_15 = "Gaby";
    private static String PASSWORD_15 = "gaby";

    private static String USERNAME_16 = "Harry";
    private static String PASSWORD_16 = "harry";

    private static String USERNAME_17 = "Stan";
    private static String PASSWORD_17 = "stan";

    private static String USERNAME_18 = "Coco";
    private static String PASSWORD_18 = "coco";

    private static String USERNAME_19 = "Avi";
    private static String PASSWORD_19 = "avi";

    private static String USERNAME_20 = "MagicStick";
    private static String PASSWORD_20 = "tom";

    // ...

    private static String NAME_COMPETITION = "H1 LinkebeekHC";
    private static String PASSWORD_COMPETITION = "lhc";
    private static String DIVISION = "2";
    private static String SEASON = "2018-2019";
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
            .withRules(4,0)
            .withTopRules(4,3,2,1)
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
        saveUser(USERNAME_11, PASSWORD_11);
        saveUser(USERNAME_12, PASSWORD_12);
        saveUser(USERNAME_13, PASSWORD_13);
        saveUser(USERNAME_14, PASSWORD_14);
        saveUser(USERNAME_15, PASSWORD_15);
        saveUser(USERNAME_16, PASSWORD_16);
        saveUser(USERNAME_17, PASSWORD_17);
        saveUser(USERNAME_18, PASSWORD_18);
        saveUser(USERNAME_19, PASSWORD_19);
        saveUser(USERNAME_20, PASSWORD_20);


        saveCompetition();

        addUserToCompetition(USERNAME_1, NAME_COMPETITION, PASSWORD_COMPETITION);
        addUserToCompetition(USERNAME_2, NAME_COMPETITION, PASSWORD_COMPETITION);
        addUserToCompetition(USERNAME_3, NAME_COMPETITION, PASSWORD_COMPETITION);
        addUserToCompetition(USERNAME_4, NAME_COMPETITION, PASSWORD_COMPETITION);
        addUserToCompetition(USERNAME_5, NAME_COMPETITION, PASSWORD_COMPETITION);
        addUserToCompetition(USERNAME_6, NAME_COMPETITION, PASSWORD_COMPETITION);
        addUserToCompetition(USERNAME_7, NAME_COMPETITION, PASSWORD_COMPETITION);
        addUserToCompetition(USERNAME_8, NAME_COMPETITION, PASSWORD_COMPETITION);
        addUserToCompetition(USERNAME_9, NAME_COMPETITION, PASSWORD_COMPETITION);
        addUserToCompetition(USERNAME_10, NAME_COMPETITION, PASSWORD_COMPETITION);
        addUserToCompetition(USERNAME_11, NAME_COMPETITION, PASSWORD_COMPETITION);
        addUserToCompetition(USERNAME_12, NAME_COMPETITION, PASSWORD_COMPETITION);
        addUserToCompetition(USERNAME_13, NAME_COMPETITION, PASSWORD_COMPETITION);
        addUserToCompetition(USERNAME_14, NAME_COMPETITION, PASSWORD_COMPETITION);
        addUserToCompetition(USERNAME_15, NAME_COMPETITION, PASSWORD_COMPETITION);
        addUserToCompetition(USERNAME_16, NAME_COMPETITION, PASSWORD_COMPETITION);
        addUserToCompetition(USERNAME_17, NAME_COMPETITION, PASSWORD_COMPETITION);
        addUserToCompetition(USERNAME_18, NAME_COMPETITION, PASSWORD_COMPETITION);
        addUserToCompetition(USERNAME_19, NAME_COMPETITION, PASSWORD_COMPETITION);
        addUserToCompetition(USERNAME_20, NAME_COMPETITION, PASSWORD_COMPETITION);


    }
}
