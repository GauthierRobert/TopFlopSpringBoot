package com.lhc.webservices.restServices;

import com.lhc.business.dto.RankingCell;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

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
}
