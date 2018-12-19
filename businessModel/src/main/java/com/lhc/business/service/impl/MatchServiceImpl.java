package com.lhc.business.service.impl;

import com.lhc.business.dto.Match;
import com.lhc.business.service.CompetitionService;
import com.lhc.business.service.MatchService;
import com.lhc.business.service.mapper.MapperHandler;
import com.lhc.datamodel.entities.BallotRecord;
import com.lhc.datamodel.entities.CompetitionRecord;
import com.lhc.datamodel.entities.MatchRecord;
import com.lhc.datamodel.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MatchServiceImpl implements MatchService {

    private MatchRepository matchRepository;
    private CompetitionService competitionService;
    private MapperHandler mapperHandler;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository, CompetitionService competitionService, MapperHandler mapperHandler) {
        this.matchRepository = matchRepository;
        this.competitionService = competitionService;
        this.mapperHandler = mapperHandler;
    }

    @Override
    public List<Match> findAllMatchesByCompetitionReference(String competition_ref) {

        List<MatchRecord> matchRecords = matchRepository.findAllByCompetitionReference(competition_ref);

        final List<Match> matches = new ArrayList<Match>();
        matchRecords.forEach(matchRecord -> {
            matches.add(mapperHandler.mapMatch(matchRecord, null));
        });

        return matches;
    }

    @Override
    public Match findMatchById(Long Id) {

        MatchRecord matchRecord = matchRepository.findById(Id).get();

        return mapperHandler.mapMatch(matchRecord, match);;
    }

    @Override
    public MatchRecord findMatchByReference(String ref) {

        return matchRepository.findByReference(ref);

    }

    @Override
    public MatchRecord saveOrUpdate(Match match, String competition_ref) {

        CompetitionRecord competitionRecord = competitionService.findByReference(competition_ref);
        String ref;
        MatchRecord matchRecord = null;
        if (match.getReference() !=null) {
            matchRecord = matchRepository.findByReference(match.getReference());
        } else {
            ref = UUID.randomUUID().toString();
            match.setReference(ref);
        }
        matchRecord = mapperHandler.mapMatchRecord(match, matchRecord);
        matchRecord.setCompetitionRecord(competitionRecord);

        return matchRepository.save(matchRecord);
    }
}
