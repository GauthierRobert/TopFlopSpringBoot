package com.lhc.mapper.ballot;

import com.lhc.datamodel.entities.competition.Ballot;
import com.lhc.dto.BallotDto;
import com.lhc.dto.VoteDto;
import com.lhc.mapper.mapperHandler.BallotMapperHandler;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.lhc.dto.builder.BallotDtoBuilder.aBallotDto;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class BallotMapperHandlerTest {

    @Test
    public void createEntityFromDTO() {

        BallotDto ballotDto = aBallotDto()
                .withReference("AAA")
                .withCompetition_ref("Linkebeek")
                .withMatch_ref("rergrgert")
                .withComment("AAA","BBB")
                .withVotesDtos().build();

        List<VoteDto> voteDtoList = new ArrayList<>();
        VoteDto voteDto_1 = VoteDto.voteDto(1, "A", 1);
        VoteDto voteDto_2 = VoteDto.voteDto(1, "A", 1);
        VoteDto voteDto_3 = VoteDto.voteDto(1, "A", 1);
        voteDtoList.add(voteDto_1);
        voteDtoList.add(voteDto_2);
        voteDtoList.add(voteDto_3);

        ballotDto.setVoteDtos(voteDtoList);

        BallotMapperHandler ballotMapperHandler = new BallotMapperHandler();

        Ballot actual = ballotMapperHandler.createEntityFromDTO(ballotDto);

        assertThat(actual.getCompetition_ref()).isNotNull();
        assertThat(actual.getCompetition_ref()).isEqualTo(ballotDto.getCompetition_ref());

        Ballot actual_2 = Ballot.ballot();
        actual_2.setId(3L);
        actual_2 = ballotMapperHandler.mapToEntity(ballotDto, actual_2);

        assertThat(actual_2.getCompetition_ref()).isNotNull();
        assertThat(actual_2.getId()).isEqualTo(3L);

    }

    @Test
    public void name() {
    }
}