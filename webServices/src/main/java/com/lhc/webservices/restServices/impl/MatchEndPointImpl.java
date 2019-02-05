package com.lhc.webservices.restServices.impl;

import com.lhc.business.service.competition.MatchService;
import com.lhc.datamodel.entities.competition.Match;
import com.lhc.dto.MatchDto;
import com.lhc.mapper.mapperHandler.MatchMapperHandler;
import com.lhc.webservices.restServices.MatchEndPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


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

}
