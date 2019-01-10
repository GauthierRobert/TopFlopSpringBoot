package com.lhc.mapper.vote;

import com.lhc.datamodel.entities.Vote;
import com.lhc.dto.VoteDto;
import com.lhc.mapper.MapperHandler;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class VoteMapperHandler implements MapperHandler<Vote, VoteDto> {

    @Override
    public Vote mapToEntity(VoteDto voteDto, Vote vote) {

        if (vote == null){
            vote = Vote.vote();
        }

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(VoteDto.class, Vote.class)
                .field("reference", "reference")
                .field("name", "name")
                .field("points", "points")
                .register();

        MapperFacade mapper = mapperFactory.getMapperFacade();

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

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Vote.class, VoteDto.class)
                .field("reference", "reference")
                .field("name", "name")
                .field("points", "points")
                .register();

        MapperFacade mapper = mapperFactory.getMapperFacade();
        mapper.map(vote, voteDto);

        return voteDto;
    }

}
