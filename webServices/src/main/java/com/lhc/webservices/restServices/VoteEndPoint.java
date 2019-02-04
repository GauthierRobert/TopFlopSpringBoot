package com.lhc.webservices.restServices;

import com.lhc.dto.VoteDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@RestController
public interface VoteEndPoint {

    @RequestMapping(
            value = "/vote/get",
            method = RequestMethod.GET)
    List<VoteDto> getVotesWithBallotRef(@RequestParam(value = "ballot_ref") String ballot_ref);
}
