package com.lhc.mapper.match;

import com.lhc.datamodel.entities.Match;
import com.lhc.dto.MatchDto;
import com.lhc.mapper.MapperHandler;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class MatchMapperHandler implements MapperHandler<Match, MatchDto> {




    @Override
    public Match mapToEntity(MatchDto matchDto, Match match) {

        if (match ==null){
            match = Match.match();
        }

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(MatchDto.class, Match.class)
                .field("homeTeam", "homeTeam")
                .field("homeScore", "homeScore")
                .field("awayTeam", "awayTeam")
                .field("awayScore", "awayScore")
                .field("date", "date")
                .field("competition_ref", "competition_ref")
                .field("creatorUsername", "creatorUsername")
                .field("reference", "reference")
                .register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        mapper.map(matchDto, match);

        return match;

    }

    @Override
    public Match createEntityFromDTO(MatchDto matchDto) {
        return mapToEntity(matchDto, Match.match());
    }

    @Override
    public MatchDto createDTOFromEntity(Match match) {
        return mapToDto(match, new MatchDto());
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
                .field("date", "date")
                .field("competition_ref", "competition_ref")
                .field("creatorUsername", "creatorUsername")
                .field("reference", "reference")
                .register();
        MapperFacade mapper = mapperFactory.getMapperFacade();

        mapper.map(match, matchDto);

        return matchDto;

    }

}
