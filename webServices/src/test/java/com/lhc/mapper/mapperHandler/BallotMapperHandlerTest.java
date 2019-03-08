package com.lhc.mapper.mapperHandler;

import com.lhc.datamodel.entities.competition.Ballot;
import com.lhc.datamodel.entities.competition.Vote;
import com.lhc.dto.BallotDto;
import com.lhc.dto.VoteDto;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import static com.lhc.dto.builder.BallotDtoBuilder.aBallotDto;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class BallotMapperHandlerTest {

    BallotDto ballotDto;
    Ballot ballot;
    private static final String LINK = "Link";

    @Before
    public void setUp() {
        ballotDto = aBallotDto()
                .withCompetition_ref("Linkebeek")
                .withMatch_ref(null)
                .withComment("AAA","BBB")
                .withVotesDtos()
                .addValidationFlopVote("Francis").build();


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

        ballot = Ballot.ballot();

        ballot.setMatch_ref(LINK);
        ballot.addVote(vote_1);
        ballot.addVote(vote_2);
        ballot.addVote(vote_3);
        ballot.addVote(vote_4);
    }

    @Test
    public void createEntityFromDTO() {



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

    }

    @Test
    public void mapEntityFromDto() {

        BallotMapperHandler ballotMapperHandler = new BallotMapperHandler();
        Ballot actual = ballotMapperHandler.mapToEntity(ballotDto, ballot);

        assertThat(actual).isEqualTo(ballot);
        assertThat(actual.getVotes().size()).isEqualTo(5);
        assertThat(actual.getMatch_ref()).isEqualTo(LINK);

    }
}