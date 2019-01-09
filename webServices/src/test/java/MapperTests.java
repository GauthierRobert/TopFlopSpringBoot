import com.lhc.business.BusinessConfig;
import com.lhc.datamodel.entities.Competition;
import com.lhc.datamodel.entities.Vote;
import com.lhc.dto.CompetitionDto;
import com.lhc.dto.VoteDto;
import com.lhc.mapper.competition.CompetitionMapperHandler;
import com.lhc.mapper.vote.VoteMapperHandler;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes =  BusinessConfig.class)
public class MapperTests {


    @Test
    public void VoteMapping(){

        VoteDto voteDto = new VoteDto();
        voteDto.setName("azerazeraz");
        voteDto.setPoints(4);
        voteDto.setReference("BE21412142");

        VoteMapperHandler voteMapperHandler = new VoteMapperHandler();
        Vote vote_mapped = voteMapperHandler.mapToEntity(voteDto, null);

        Assert.assertEquals(voteDto.getReference(), vote_mapped.getReference());

        Vote vote = new Vote();
        vote.setId(Long.valueOf("545454"));
        vote.setPoints(0);
        VoteDto voteDto_mapped = voteMapperHandler.mapToDto(vote, null);

        Assert.assertEquals(vote.getReference(), voteDto_mapped.getReference());
        Assert.assertEquals(vote.getPoints(), voteDto_mapped.getPoints());
        Assert.assertEquals(Integer.valueOf("0"), voteDto_mapped.getPoints());

    }

    @Test
    public void ListCompetitionMapping() {

        List<Competition> competitionList = new ArrayList<>();
        Competition competition = new Competition();
        competition.setName("Linkebeek");
        competition.setSeason(2018);
        competition.setPassword("AAAA");
        competitionList.add(competition);

        CompetitionMapperHandler competitionMapperHandler = new CompetitionMapperHandler();
        List<CompetitionDto> competitionDtos = competitionMapperHandler.mapToListDtos(competitionList);
        int actual = competitionDtos.size();

        assertThat(actual).isEqualTo(competitionList.size());

    }
}
