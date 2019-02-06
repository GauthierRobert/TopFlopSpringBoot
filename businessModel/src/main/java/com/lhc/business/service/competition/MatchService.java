package com.lhc.business.service.competition;

import com.lhc.datamodel.entities.competition.Match;

import java.util.List;

public interface MatchService {

    List<Match> findAllMatchesByCompetitionReference(String competition_ref);

    Match findMatchByReference(String ref);

    Match saveOrUpdate(Match match);
    
    Match close(String match_ref);

    Match open(String match_ref);
}
