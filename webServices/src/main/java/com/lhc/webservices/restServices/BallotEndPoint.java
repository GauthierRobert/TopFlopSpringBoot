package com.lhc.webservices.restServices;

import com.lhc.dto.BallotDto;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@RestController
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
