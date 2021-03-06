package com.lhc.mapper.mapperHandler;

import com.lhc.datamodel.entities.competition.Ballot;
import com.lhc.datamodel.entities.competition.Vote;
import com.lhc.dto.BallotDto;
import com.lhc.mapper.MapperHandler;
import com.lhc.mapper.singletonMapper.BallotSingletonMapper;
import ma.glasnost.orika.MapperFacade;

public class BallotMapperHandler implements MapperHandler<Ballot, BallotDto> {

    @Override
    public Ballot mapToEntity(BallotDto ballotDto, Ballot ballot) {

        if (ballot ==null){
            ballot = Ballot.ballot();
        }

        MapperFacade mapper = BallotSingletonMapper.getInstanceEntity();
        mapper.map(ballotDto, ballot);

        VoteMapperHandler voteMapperHandler = new VoteMapperHandler();
        if (ballotDto.getVoteDtos() !=null) {
            ballot.getVotes().addAll(voteMapperHandler.mapToListEntities(ballotDto.getVoteDtos()));
            for(Vote vote:  ballot.getVotes()){
                vote.setBallot(ballot);
            }
        }

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

        MapperFacade mapper = BallotSingletonMapper.getInstanceDto();
        mapper.map(ballot, ballotDto);

        VoteMapperHandler voteMapperHandler = new VoteMapperHandler();
        if (ballot.getVotes() !=null) {
            ballotDto.setVoteDtos(voteMapperHandler.mapToListDtos(ballot.getVotes()));
        }
        if (ballot.getUser() !=null) {
            ballotDto.setUsername(ballot.getUser().getUsername());
        }

        return ballotDto;
    }

}
