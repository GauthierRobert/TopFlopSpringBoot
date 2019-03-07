package com.lhc.webservices.restServices.impl;

import com.lhc.business.BusinessConfig;
import com.lhc.business.service.competition.BallotService;
import com.lhc.datamodel.entities.competition.Ballot;
import com.lhc.dto.BallotDto;
import com.lhc.dto.builder.BallotDtoBuilder;
import com.lhc.mapper.mapperHandler.BallotMapperHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static com.lhc.dto.builder.BallotDtoBuilder.aCountedBallotDto;
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes =  BusinessConfig.class)
public class BallotEndPointImplTest {


    @Autowired
    private BallotService ballotService;

    @Test
    public void updateBallot() {
        BallotDtoBuilder.VoteDtoBuilder dtoBuilder = aCountedBallotDto().withReference("f4f16cde-e2b7-4b24-a256-94159c4bff72").withVotesDtos();
        String playerValidation = "AmauryM";
        dtoBuilder.addValidationFlopVote(playerValidation);
        BallotDto ballotDto = dtoBuilder.build();


        BallotMapperHandler ballotMapperHandler = new BallotMapperHandler();
        Ballot ballot = Ballot.ballot();

        if (ballotDto.getReference() != null) {
            ballot = ballotService.findByReference(ballotDto.getReference());
        }

        ballotMapperHandler.mapToEntity(ballotDto, ballot);
        ballotService.saveOrUpdate(ballot);
    }
}