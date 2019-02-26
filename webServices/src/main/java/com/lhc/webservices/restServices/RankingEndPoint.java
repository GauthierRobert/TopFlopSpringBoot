package com.lhc.webservices.restServices;

import com.lhc.business.dto.RankingCell;
import com.lhc.dto.Rankings;
import java.util.List;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Produces(MediaType.APPLICATION_JSON)
public interface RankingEndPoint {

    @RequestMapping(
            value = "/ranking/top",
            method = RequestMethod.GET)
    List<RankingCell> getRankingTop(@RequestParam(value = "match_ref") String match_ref);

    @RequestMapping(
            value = "/ranking/flop",
            method = RequestMethod.GET)
    List<RankingCell> getRankingFlop(@RequestParam(value = "match_ref") String match_ref);


    @RequestMapping(
            value = "/ranking/topflop",
            method = RequestMethod.GET)
    Rankings getRankings(@RequestParam(value = "match_ref") String match_ref);

    @RequestMapping(
            value = "/ranking/intermediate",
            method = RequestMethod.GET)
    Rankings getIntermediateRankings(@RequestParam(value = "match_ref") String match_ref);

}
