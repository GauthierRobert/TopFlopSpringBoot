package com.lhc.webservices.restServices.impl;

import com.lhc.business.service.competition.BallotService;
import com.lhc.business.service.security.UserService;
import com.lhc.datamodel.entities.competition.Ballot;
import com.lhc.datamodel.entities.security.User;
import com.lhc.dto.BallotDto;
import com.lhc.mapper.mapperHandler.BallotMapperHandler;
import com.lhc.webservices.restServices.BallotEndPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class BallotEndPointImpl implements BallotEndPoint {

    @Autowired
    private BallotService ballotService;
    @Autowired
    private UserService userService;

    @Override
    public BallotDto postBallot(@RequestBody BallotDto ballotDto, @RequestParam String username){

        User currentUser = userService.findByUsername(username);

        BallotMapperHandler ballotMapperHandler = new BallotMapperHandler();
        Ballot ballot = ballotMapperHandler.createEntityFromDTO(ballotDto);
        ballot.setUser(currentUser);

        ballotService.saveOrUpdate(ballot);

        return ballotDto;
    }



    @Override
    public List<BallotDto> getBallotsWithMatchRef(@RequestParam(value = "match_ref") String match_ref){

        List<Ballot> ballots = ballotService.findAllBallotsByMatchReference(match_ref);

        BallotMapperHandler ballotMapperHandler = new BallotMapperHandler();
        
        return ballotMapperHandler.mapToListDtos(ballots);

    }


}
