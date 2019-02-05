package com.lhc.webservices.restServices;

import com.lhc.dto.MatchDto;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
@RestController
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
}
