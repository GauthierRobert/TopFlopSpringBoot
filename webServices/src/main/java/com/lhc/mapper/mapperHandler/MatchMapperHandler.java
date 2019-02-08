package com.lhc.mapper.mapperHandler;

import com.lhc.datamodel.entities.competition.Match;
import com.lhc.dto.MatchDto;
import com.lhc.mapper.MapperHandler;
import ma.glasnost.orika.MapperFacade;

import java.util.List;

import static com.lhc.dto.MatchDto.matchDto;
import static com.lhc.mapper.singletonMapper.MatchSingletonMapper.getInstanceDto;
import static com.lhc.mapper.singletonMapper.MatchSingletonMapper.getInstanceEntity;

public class MatchMapperHandler implements MapperHandler<Match, MatchDto> {


    @Override
    public Match mapToEntity(MatchDto matchDto, Match match) {

        if (match == null) {
            match = Match.match();
        }

        MapperFacade mapper = getInstanceEntity();
        mapper.map(matchDto, match);

        VisitorsMapper visitorsMapper = new VisitorsMapper();
        String visitors = visitorsMapper.mapListVisitorIntoString(matchDto.getVisitors());
        match.setVisitors(visitors);

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

        if (matchDto == null) {
            matchDto = matchDto();
        }


        MapperFacade mapper = getInstanceDto();
        mapper.map(match, matchDto);
        matchDto.setDate(match.getDate().toString());
        matchDto.setStatus(match.getStatus().name());

        VisitorsMapper visitorsMapper = new VisitorsMapper();
        List<String> visitors = visitorsMapper.mapStringVisitorIntoList(match.getVisitors());
        matchDto.setVisitors(visitors);

        return matchDto;

    }

}
