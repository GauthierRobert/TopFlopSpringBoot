package com.lhc.business.service;

import com.lhc.datamodel.entities.Match;

import java.util.List;

public interface MatchService {

    List<Match> findAllMatchesByCompetitionReference(String competition_ref);

    Match findMatchById(Long Id);

    Match findMatchByReference(String ref);

    Match saveOrUpdate(Match match, String comp_ref);
    
    Match close(String match_ref);

    Match open(String match_ref);
}
