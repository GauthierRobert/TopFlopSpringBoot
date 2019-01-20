package com.lhc.mapper.match;

import com.lhc.datamodel.entities.Match;
import com.lhc.dto.MatchDto;
import com.lhc.mapper.MapperHandler;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import static com.lhc.dto.MatchDto.matchDto;
import static com.lhc.mapper.match.MatchSingletonMapper.getInstanceDto;
import static com.lhc.mapper.match.MatchSingletonMapper.getInstanceEntity;

public class MatchMapperHandler implements MapperHandler<Match, MatchDto> {




    @Override
    public Match mapToEntity(MatchDto matchDto, Match match) {

        if (match ==null){
            match = Match.match();
        }

        MapperFacade mapper = getInstanceEntity();
        mapper.map(matchDto, match);

        return match;

    }

    @Override
    public Match createEntityFromDTO(MatchDto matchDto) {
        return mapToEntity(matchDto, Match.match());
    }

    @Override
    public MatchDto createDTOFromEntity(Match match) {
        return mapToDto(match, matchDto());
    }


    @Override
    public MatchDto mapToDto(Match match, MatchDto matchDto) {

        if (matchDto ==null){
            matchDto = matchDto();
        }


        MapperFacade mapper = getInstanceDto();
        mapper.map(match, matchDto);
        matchDto.setDate(match.getDate().toString());
        matchDto.setStatus(match.getStatus().name());
        return matchDto;

    }

}
