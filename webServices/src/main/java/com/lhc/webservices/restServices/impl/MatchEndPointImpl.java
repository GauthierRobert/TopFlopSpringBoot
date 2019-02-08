package com.lhc.webservices.restServices.impl;

import com.lhc.business.service.competition.MatchService;
import com.lhc.datamodel.entities.competition.Match;
import com.lhc.dto.MatchDto;
import com.lhc.mapper.mapperHandler.MatchMapperHandler;
import com.lhc.webservices.restServices.MatchEndPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MatchEndPointImpl implements MatchEndPoint {


    @Autowired
    private MatchService matchService;

    @Override
    public MatchDto postMatch(@RequestBody MatchDto matchDto){

        MatchMapperHandler matchMapperHandler = new MatchMapperHandler();
        Match match = matchMapperHandler.createEntityFromDTO(matchDto);
        matchService.saveOrUpdate(match);
        return matchDto;
    }

    @Override
    public MatchDto addVisitors(MatchDto matchDto) {

        MatchMapperHandler matchMapperHandler = new MatchMapperHandler();
        Match match = matchMapperHandler.createEntityFromDTO(matchDto);
        match = matchService.addVisitors(match.getReference(), match.getVisitors());
        MatchDto matchDtoUpdate = matchMapperHandler.createDTOFromEntity(match);
        return matchDtoUpdate;
    }


    @Override
    public MatchDto closeMatch(@RequestParam(value = "match_ref") String match_ref){
        Match match = matchService.close(match_ref);
        MatchMapperHandler matchMapperHandler = new MatchMapperHandler();
        MatchDto matchDto = matchMapperHandler.createDTOFromEntity(match);
        return matchDto;
    }

    @Override
    public MatchDto openMatch(@RequestParam(value = "match_ref") String match_ref){
        Match match = matchService.open(match_ref);
        MatchMapperHandler matchMapperHandler = new MatchMapperHandler();
        MatchDto matchDto = matchMapperHandler.createDTOFromEntity(match);
        return matchDto;
    }

    @Override
    public List<MatchDto> getMatchesWithCompetitionRef(@RequestParam(value = "competition_ref") String competition_ref){

        List<Match> matches = matchService.findAllMatchesByCompetitionReference(competition_ref);

        MatchMapperHandler matchMapperHandler = new MatchMapperHandler();
        return matchMapperHandler.mapToListDtos(matches);

    }

}