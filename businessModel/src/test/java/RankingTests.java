import com.lhc.business.BusinessConfig;
import com.lhc.business.service.RankingService;
import com.lhc.datamodel.entities.Ballot;
import com.lhc.datamodel.entities.Match;
import com.lhc.datamodel.entities.Vote;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes =  BusinessConfig.class)
public class RankingTests {

    @Autowired
    private RankingService rankingService;

    @Test
    public void createRanking(){

        Vote vote_1 = Vote.vote();
        vote_1.setName("A");
        vote_1.setPoints(1);
        Vote vote_2 = Vote.vote();
        vote_2.setName("B");
        vote_2.setPoints(2);
        Vote vote_3 = Vote.vote();
        vote_3.setName("C");
        vote_3.setPoints(3);
        Vote vote_4 = Vote.vote();
        vote_4.setName("D");
        vote_4.setPoints(4);

        Ballot ballot = Ballot.ballot();
        List<Vote> votes = new ArrayList<>();
        votes.add(vote_1);
        votes.add(vote_2);
        votes.add(vote_3);
        votes.add(vote_4);
        ballot.setVotes(votes);

        Match match = Match.match();
        List<Ballot> ballots = new ArrayList<>();
        ballots.add(ballot);
        ballots.add(ballot);
        ballots.add(ballot);
        match.setBallots(ballots);

        Map<String, Integer> ranking = rankingService.createTopFlopByMatch(match);

        Assert.assertEquals(ranking.get("D"), new Integer(12));

    }


}
