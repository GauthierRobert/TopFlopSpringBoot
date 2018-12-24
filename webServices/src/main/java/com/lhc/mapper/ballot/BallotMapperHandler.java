package com.lhc.business.service.mapper.ballot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BallotMapperHandler implements MapperHandler<Ballot, BallotDto> {

    @Override
    public Ballot mapToEntitity(BallotDto ballotDto, Ballot ballot) {

        if (ballot ==null){
            ballot = new Ballot();
        }

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(BallotDto.class, Ballot.class)
                .field("reference", "reference")
                .register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        mapper.map(ballotDto, ballot);

        List<Vote> votes = new ArrayList<>();
        final String ref_f = ref;
        final AtomicInteger i = new AtomicInteger(0);
        final Ballot finalBallot = ballot;
        ballotDto.getVotes().forEach(voteDto -> {
            i.addAndGet(1);
            Vote vote = new Vote();
            MapperHandler voteMapperHandler = new VoteMapperHandler();
            voteMapperHandler.mapToEntity(voteDto, vote);
            vote.setBallot(finalBallot);
            vote.setReference(ref_f +"_"+ i.toString());
            votes.add(vote);
        });

        return ballot;

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
