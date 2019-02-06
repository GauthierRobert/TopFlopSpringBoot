package com.lhc.webservices.restServices;

import com.lhc.dto.MatchDto;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
public interface MatchEndPoint {

    @RequestMapping(
            value = "/match/save",
            method = RequestMethod.POST)
    MatchDto postMatch(@RequestBody MatchDto matchDto);

    @RequestMapping(
            value = "/match/close",
            method = RequestMethod.POST)
    MatchDto closeMatch(@RequestParam(value = "match_ref") String match_ref);

    @RequestMapping(
            value = "/match/open",
            method = RequestMethod.POST)
    MatchDto openMatch(@RequestParam(value = "match_ref") String match_ref);


    @RequestMapping(
            value = "/match/get",
            method = RequestMethod.GET)
    List<MatchDto> getMatchesWithCompetitionRef(@RequestParam(value = "competition_ref") String competition_ref);
}
