package com.lhc.business.service;

import java.util.List;

public interface MatchService {

    List<Match> findAllMatchesByCompetitionReference(String competition_ref);

    Match findMatchById(Long Id);

    Match findMatchByReference(String ref);

    Match saveOrUpdate(Match match, String comp_ref);

}
