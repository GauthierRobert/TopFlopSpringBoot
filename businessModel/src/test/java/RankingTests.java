import com.lhc.business.BusinessConfig;
import com.lhc.business.dto.Ballot;
import com.lhc.business.dto.Match;
import com.lhc.business.dto.Vote;
import com.lhc.business.service.RankingService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes =  BusinessConfig.class)
public class RankingTests {

    @Autowired
    private RankingService rankingService;

    @Test
    public void createRanking(){

        Vote vote_1 = new Vote();
        vote_1.setName("A");
        vote_1.setPoints(1);
        Vote vote_2 = new Vote();
        vote_2.setName("B");
        vote_2.setPoints(2);
        Vote vote_3 = new Vote();
        vote_3.setName("C");
        vote_3.setPoints(3);
        Vote vote_4 = new Vote();
        vote_4.setName("D");
        vote_4.setPoints(4);

        Ballot ballot = new Ballot();
        ballot.getVotes().add(vote_1);
        ballot.getVotes().add(vote_2);
        ballot.getVotes().add(vote_3);
        ballot.getVotes().add(vote_4);

        Match match = new Match();
        match.getBallots().add(ballot);
        match.getBallots().add(ballot);
        match.getBallots().add(ballot);

        Map<String, Integer> ranking = rankingService.createTopFlopByMatch(match);

        Assert.assertEquals(ranking.get("D"), new Integer(12));

    }


}
