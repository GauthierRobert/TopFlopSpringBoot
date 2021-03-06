package com.lhc.business.service.competition;

import com.lhc.business.dto.RankingCell;
import com.lhc.datamodel.entities.competition.Match;
import com.lhc.datamodel.entities.competition.Vote;

import java.util.List;
import java.util.Map;

public interface RankingService {

    Map<String, Integer> createTopFlopByMatch(Match match);

    List<RankingCell> createTopByListVote(List<Vote> votes);

    List<RankingCell> createFlopByListVote(List<Vote> votes);

}
