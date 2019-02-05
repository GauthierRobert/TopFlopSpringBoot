package com.lhc.webservices.restServices.impl;

import com.lhc.business.service.competition.VoteService;
import com.lhc.datamodel.entities.competition.Vote;
import com.lhc.dto.VoteDto;
import com.lhc.mapper.mapperHandler.VoteMapperHandler;
import com.lhc.webservices.restServices.VoteEndPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VoteEndPointImpl implements VoteEndPoint {

    @Autowired
    private VoteService voteService;


    @Override
    public List<VoteDto> getVotesWithBallotRef(@RequestParam(value = "ballot_ref") String ballot_ref){

        List<Vote> votes = voteService.findAllByBallotReference(ballot_ref);
        VoteMapperHandler voteMapperHandler = new VoteMapperHandler();
        
        return voteMapperHandler.mapToListDtos(votes);

    }

}
