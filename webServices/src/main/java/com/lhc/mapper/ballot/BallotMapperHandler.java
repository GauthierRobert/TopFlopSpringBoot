package com.lhc.mapper.ballot;

import com.lhc.datamodel.entities.Ballot;
import com.lhc.datamodel.entities.Vote;
import com.lhc.dto.BallotDto;
import com.lhc.mapper.MapperHandler;
import com.lhc.mapper.vote.VoteMapperHandler;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BallotMapperHandler implements MapperHandler<Ballot, BallotDto> {

    @Override
    public Ballot mapToEntity(BallotDto ballotDto, Ballot ballot) {

        if (ballot ==null){
            ballot = Ballot.ballot();
        }

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(BallotDto.class, Ballot.class)
                .field("reference", "reference")
                .field("match_ref", "match_ref")
                .field("competition_ref", "competition_ref")
                .register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        mapper.map(ballotDto, ballot);

        List<Vote> votes = new ArrayList<>();
        final String ref_f = ballotDto.getReference();
        final AtomicInteger i = new AtomicInteger(0);
        final Ballot finalBallot = ballot;
        ballotDto.getVoteDtos().forEach(voteDto -> {
            i.addAndGet(1);
            VoteMapperHandler voteMapperHandler = new VoteMapperHandler();
            Vote vote = voteMapperHandler.createEntityFromDTO(voteDto);
            vote.setBallot(finalBallot);
            vote.setReference(ref_f +"_"+ i.toString());
            votes.add(vote);
        });

        return ballot;

    }

    @Override
    public Ballot createEntityFromDTO(BallotDto ballotDto) {
        return mapToEntity(ballotDto, Ballot.ballot());
    }

    @Override
    public BallotDto createDTOFromEntity(Ballot ballot) {
        return mapToDto(ballot, new BallotDto());
    }

    @Override
    public BallotDto mapToDto(Ballot ballot, BallotDto ballotDto) {

        if (ballotDto ==null){
            ballotDto = new BallotDto();
        }

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Ballot.class, BallotDto.class)
                .field("reference", "reference")
                .register();

        MapperFacade mapper = mapperFactory.getMapperFacade();

        mapper.map(ballot, ballotDto);

        return ballotDto;
    }

}
