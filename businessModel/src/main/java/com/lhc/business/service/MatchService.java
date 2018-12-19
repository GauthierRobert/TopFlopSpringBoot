package com.lhc.business.service;

import com.lhc.business.dto.Match;
import com.lhc.datamodel.entities.MatchRecord;

import java.util.List;

public interface MatchService {

    List<Match> findAllMatchesByCompetitionReference(String competition_ref);

    Match findMatchById(Long Id);

    MatchRecord findMatchByReference(String ref);

    MatchRecord saveOrUpdate(Match match, String comp_ref);

}
