package com.lhc.webservices.restServices;

import com.lhc.dto.MatchDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
            value = "/match/get",
            method = RequestMethod.GET)
    MatchDto getMatch(@RequestParam("match_ref") String match_ref);

    @RequestMapping(
            value = "/match/addSpectator",
            method = RequestMethod.POST)
    MatchDto addVisitors(@RequestBody MatchDto matchDto);

    @RequestMapping(
            value = "/match/close",
            method = RequestMethod.POST)
    MatchDto closeMatch(@RequestParam(value = "match_ref") String match_ref);

    @RequestMapping(
            value = "/match/open",
            method = RequestMethod.POST)
    MatchDto openMatch(@RequestParam(value = "match_ref") String match_ref);



    @RequestMapping(
            value = "/match/getList",
            method = RequestMethod.GET)
    List<MatchDto> getMatchesWithCompetitionRef(@RequestParam(value = "competition_ref") String competition_ref);
}
