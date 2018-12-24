package com.lhc.business.service.mapper.vote;

public class VoteMapperHandler implements MapperHandler<Vote, VoteRecord>{

    @Override
    public Vote mapToDto(VoteRecord voteRecord, Vote vote) {

        if (vote ==null){
            vote = new Vote();
        }

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(VoteRecord.class, Vote.class)
                .field("reference", "reference")
                .field("name", "name")
                .field("points", "points")
                .register();

        MapperFacade mapper = mapperFactory.getMapperFacade();

        mapper.map(voteRecord, vote);

        return vote;
    }


    @Override
    public VoteRecord mapToDto(Vote vote, VoteRecord voteRecord) {

        if (voteRecord == null) {
            voteRecord = new VoteRecord();
        }

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Vote.class, VoteRecord.class)
                .field("reference", "reference")
                .field("name", "name")
                .field("points", "points")
                .register();

        MapperFacade mapper = mapperFactory.getMapperFacade();
        mapper.map(vote, voteRecord);

        return voteRecord;
    }

}
