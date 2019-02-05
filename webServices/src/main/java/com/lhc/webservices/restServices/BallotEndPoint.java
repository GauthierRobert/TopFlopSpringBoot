package com.lhc.webservices.restServices;

import com.lhc.dto.BallotDto;
import org.springframework.web.bind.annotation.*;


import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
public interface BallotEndPoint {
    @RequestMapping(
            value = "/ballot/save",
            method = RequestMethod.POST)
    BallotDto postBallot(@RequestBody BallotDto ballotDto, @RequestParam String username);

    @RequestMapping(
            value = "/ballot/get",
            method = RequestMethod.GET)
    List<BallotDto> getBallotsWithMatchRef(@RequestParam(value = "match_ref") String match_ref);
}
