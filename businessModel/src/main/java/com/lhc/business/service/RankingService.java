package com.lhc.business.service;

import com.lhc.business.dto.Match;
import com.lhc.business.dto.Vote;

import java.util.List;
import java.util.Map;

public interface RankingService {

    Map<String, Integer> createTopFlopByMatch(Match match);

    Map<String, Integer> createTopFlopByListVote(List<Vote> votes);

}
