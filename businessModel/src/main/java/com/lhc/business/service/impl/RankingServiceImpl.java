package com.lhc.business.service.impl;

import com.lhc.business.dto.RankingCell;
import com.lhc.business.service.BallotService;
import com.lhc.business.service.MatchService;
import com.lhc.business.service.RankingService;
import com.lhc.datamodel.entities.Match;
import com.lhc.datamodel.entities.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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
    public List<RankingCell> createTopByListVote(List<Vote> votes) {
        final Map<String, Integer> ranking = new HashMap<>();

        votes.forEach(vote -> {
            if(vote.getIndication() > 0) {
                putElement(ranking, vote);
            }
        });

        return createRankingCellList(getSortCollect(ranking));
    }

    @Override
    public List<RankingCell> createFlopByListVote(List<Vote> votes) {
        final Map<String, Integer> ranking = new HashMap<>();

        votes.forEach(vote -> {
                if(vote.getIndication() < 0) {
                    putElement(ranking, vote);
                }
        });

        return createRankingCellList(getSortCollect(ranking));
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
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    private List<RankingCell> createRankingCellList(Map<String, Integer> rankingMap) {
        int position = 1;
        List<RankingCell> rankingCells = new ArrayList<>();
        while (rankingMap.entrySet().iterator().hasNext()) {
            Map.Entry<String, Integer> entry = rankingMap.entrySet().iterator().next();
            String key = entry.getKey();
            Integer value = entry.getValue();
            RankingCell rankingCell = new RankingCell(position + ".", key, value);
            rankingCells.add(rankingCell);
            position++;
        }

        return rankingCells;
    }

}
