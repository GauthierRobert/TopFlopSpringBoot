package com.lhc.webservices.restServices;

import com.lhc.dto.VoteDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
public interface VoteEndPoint {

    @RequestMapping(
            value = "/vote/getList",
            method = RequestMethod.GET)
    List<VoteDto> getVotesWithBallotRef(@RequestParam(value = "ballot_ref") String ballot_ref);
}
