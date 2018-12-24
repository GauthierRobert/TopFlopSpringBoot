package com.lhc.business.service.mapper.vote;

public class VoteMapperHandler implements MapperHandler<Vote, VoteDto>{

    @Override
    public Vote mapToEntity(VoteDto voteDto, Vote vote) {

        if (vote ==null){
            vote = new Vote();
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
