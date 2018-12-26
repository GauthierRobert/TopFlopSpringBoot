package com.lhc.business.service;

import com.lhc.datamodel.entities.Match;
import com.lhc.datamodel.entities.Vote;

import java.util.List;
import java.util.Map;

public interface RankingService {

    Map<String, Integer> createTopFlopByMatch(Match match);

    Map<String, Integer> createTopFlopByListVote(List<Vote> votes);

}
