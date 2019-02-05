import com.lhc.datamodel.entities.competition.Competition;
import com.lhc.datamodel.entities.competition.Vote;
import com.lhc.dto.CompetitionDto;
import com.lhc.dto.VoteDto;
import com.lhc.mapper.competition.CompetitionMapperHandler;
import com.lhc.mapper.vote.VoteMapperHandler;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.lhc.dto.builder.CompetitionDtoBuilderTest.competitionDtoTest;
import static org.assertj.core.api.Assertions.assertThat;


public class MapperTests {

    private static final String COMPETITION_NAME = "Linkebeek";

    @Test
    public void VoteMapping(){

        VoteDto voteDto = new VoteDto();
        voteDto.setName("azerazeraz");
        voteDto.setPoints(4);
        voteDto.setReference("BE21412142");

        VoteMapperHandler voteMapperHandler = new VoteMapperHandler();
        Vote vote_mapped = voteMapperHandler.mapToEntity(voteDto, null);

        Assert.assertEquals(voteDto.getReference(), vote_mapped.getReference());

        Vote vote = Vote.vote();
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
        Competition competition = getCompetition();
        competitionList.add(competition);

        CompetitionMapperHandler competitionMapperHandler = new CompetitionMapperHandler();
        List<CompetitionDto> actual = competitionMapperHandler.mapToListDtos(competitionList);


        assertThat(actual.get(0).getName()).isEqualTo(COMPETITION_NAME);
        assertThat(actual.size()).isEqualTo(competitionList.size());

    }

    @Test
    public void createDTO() {
        Competition competition = getCompetition();
        CompetitionMapperHandler competitionMapperHandler = new CompetitionMapperHandler();
        CompetitionDto actual = competitionMapperHandler.createDTOFromEntity(competition);

        assertThat(actual.getName()).isEqualTo(COMPETITION_NAME);

    }

    private Competition getCompetition() {

        Competition competition = Competition.competition();
        competition.setName(COMPETITION_NAME);
        competition.setSeason("2018");
        competition.setPassword("AAAA");
        competition.setDivision("D1");
        competition.setCreatorUsername("AAAA");

        return competition;
    }

    @Test
    public void CreateEntityFromDto_Competition() {

        CompetitionMapperHandler competitionMapperHandler =  new CompetitionMapperHandler();
        Competition actual = competitionMapperHandler.createEntityFromDTO(competitionDtoTest);

        assertThat(actual.getDivision()).isEqualTo(competitionDtoTest.getDivision());
    }
}
