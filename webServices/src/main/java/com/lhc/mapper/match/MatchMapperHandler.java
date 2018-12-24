package com.lhc.business.service.mapper.match;

public class MatchMapperHandler implements MapperHandler<Match, MatchRecord> {




    @Override
    public Match mapToEntity(MatchDto matchDto, Match match) {

        if (match ==null){
            match = new Match();
        }

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(MatchDto.class, Match.class)
                .field("homeTeam", "homeTeam")
                .field("homeScore", "homeScore")
                .field("awayTeam", "awayTeam")
                .field("awayScore", "awayScore")
                .field("reference", "reference")
                .register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        mapper.map(matchDto, match);

        return match;

    }





    @Override
    public MatchDto mapToDto(Match match, MatchDto matchDto) {

        if (matchDto ==null){
            matchDto = new MatchDto();
        }

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Match.class, MatchDto.class)
                .field("homeTeam", "homeTeam")
                .field("homeScore", "homeScore")
                .field("awayTeam", "awayTeam")
                .field("awayScore", "awayScore")
                .field("reference", "reference")
                .register();
        MapperFacade mapper = mapperFactory.getMapperFacade();

        mapper.map(match, matchDto);

        return matchDto;

    }

}
