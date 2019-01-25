package com.lhc.business.service;

import com.lhc.business.service.impl.RankingCell;
import com.lhc.datamodel.entities.Match;
import com.lhc.datamodel.entities.Vote;

import java.util.List;
import java.util.Map;

public interface RankingService {

    Map<String, Integer> createTopFlopByMatch(Match match);

    List<RankingCell> createTopByListVote(List<Vote> votes);

    List<RankingCell> createFlopByListVote(List<Vote> votes);

}
