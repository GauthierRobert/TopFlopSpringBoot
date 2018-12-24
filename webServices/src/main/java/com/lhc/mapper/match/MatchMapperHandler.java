package com.lhc.business.service.mapper.match;

public class MatchMapperHandler implements MapperHandler<Match, MatchRecord> {




    @Override
    public Match map(MatchRecord matchRecord, Match match) {

        if (match ==null){
            match = new Match();
        }

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(MatchRecord.class, Match.class)
                .field("homeTeam", "homeTeam")
                .field("homeScore", "homeScore")
                .field("awayTeam", "awayTeam")
                .field("awayScore", "awayScore")
                .field("reference", "reference")
                .register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        mapper.map(matchRecord, match);

        return match;

    }





    @Override
    public MatchRecord mapToDto(Match match, MatchRecord matchRecord) {

        if (matchRecord ==null){
            matchRecord = new MatchRecord();
        }

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Match.class, MatchRecord.class)
                .field("homeTeam", "homeTeam")
                .field("homeScore", "homeScore")
                .field("awayTeam", "awayTeam")
                .field("awayScore", "awayScore")
                .field("reference", "reference")
                .register();
        MapperFacade mapper = mapperFactory.getMapperFacade();

        mapper.map(match, matchRecord);

        return matchRecord;

    }

}
