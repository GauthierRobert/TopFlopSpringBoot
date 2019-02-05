package com.lhc.mapper.vote;

import com.lhc.datamodel.entities.competition.Vote;
import com.lhc.dto.VoteDto;
import com.lhc.mapper.MapperHandler;
import ma.glasnost.orika.MapperFacade;

public class VoteMapperHandler implements MapperHandler<Vote, VoteDto> {

    @Override
    public Vote mapToEntity(VoteDto voteDto, Vote vote) {

        if (vote == null){
            vote = Vote.vote();
        }

        MapperFacade mapper = VoteSingletonMapper.getInstanceEntity();
        mapper.map(voteDto, vote);

        return vote;
    }

    @Override
    public Vote createEntityFromDTO(VoteDto voteDto) {
        return mapToEntity(voteDto, Vote.vote());
    }

    @Override
    public VoteDto createDTOFromEntity(Vote vote) {
        return mapToDto(vote, new VoteDto());
    }


    @Override
    public VoteDto mapToDto(Vote vote, VoteDto voteDto) {

        if (voteDto == null) {
            voteDto = new VoteDto();
        }

        MapperFacade mapper = VoteSingletonMapper.getInstanceDto();
        mapper.map(vote, voteDto);

        return voteDto;
    }

}
