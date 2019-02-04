package com.lhc.webservices.restServices.impl;

import com.lhc.business.service.VoteService;
import com.lhc.datamodel.entities.Vote;
import com.lhc.dto.VoteDto;
import com.lhc.mapper.vote.VoteMapperHandler;
import com.lhc.webservices.restServices.VoteEndPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


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
