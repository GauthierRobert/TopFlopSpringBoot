package com.lhc.business.service.impl;

import com.lhc.business.service.BallotService;
import com.lhc.business.service.MatchService;
import com.lhc.business.service.RankingService;
import com.lhc.datamodel.entities.Match;
import com.lhc.datamodel.entities.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RankingServiceImpl implements RankingService {

    private MatchService matchService;
    private BallotService ballotService;


    @Autowired
    public RankingServiceImpl(MatchService matchService, BallotService ballotService) {
        this.matchService = matchService;
        this.ballotService = ballotService;
    }

    @Override
    public Map<String, Integer> createTopFlopByMatch(Match match) {

        final Map<String, Integer> ranking = new HashMap<>();

        if (match.getBallots() !=null) {
            match.getBallots().forEach(ballot -> {
                if (ballot.getVotes() !=null){
                    ballot.getVotes().forEach(vote -> {
                        putElement(ranking, vote);
                    });
                }
            });
        }

        return getSortCollect(ranking);

    }

    @Override
    public Map<String, Integer> createTopFlopByListVote(List<Vote> votes) {
        final Map<String, Integer> ranking = new HashMap<>();

        votes.forEach(vote -> putElement(ranking, vote));

        return getSortCollect(ranking);
    }

    private void putElement(Map<String, Integer> ranking, Vote vote) {
        String name = vote.getName();

        if (ranking.containsKey(name)) {
            Integer actualPoints = ranking.get(name);
            if (actualPoints !=null){
                ranking.put(name, actualPoints + vote.getPoints());
            }
        } else {
            ranking.put(name, vote.getPoints());
        }
    }

    private LinkedHashMap<String, Integer> getSortCollect(Map<String, Integer> ranking) {
        return ranking.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

}
