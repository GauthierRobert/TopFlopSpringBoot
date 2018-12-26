package com.lhc.business.service.impl;

import com.lhc.business.service.CompetitionService;
import com.lhc.business.service.MatchService;
import com.lhc.datamodel.entities.Competition;
import com.lhc.datamodel.entities.Match;
import com.lhc.datamodel.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MatchServiceImpl implements MatchService {

    private MatchRepository matchRepository;
    private CompetitionService competitionService;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository, CompetitionService competitionService) {
        this.matchRepository = matchRepository;
        this.competitionService = competitionService;
    }

    @Override
    public List<Match> findAllMatchesByCompetitionReference(String competition_ref) {

        return matchRepository.findAllByCompetitionReference(competition_ref);
    }

    @Override
    public Match findMatchById(Long Id) {

        return matchRepository.findById(Id).get();
    }

    @Override
    public Match findMatchByReference(String ref) {

        return matchRepository.findByReference(ref);

    }

    @Override
    public Match saveOrUpdate(Match match, String competition_ref) {

        Competition competition = competitionService.findByReference(competition_ref);
        String ref;

        if (match.getReference() !=null) {
            match = matchRepository.findByReference(match.getReference());
        } else {
            ref = UUID.randomUUID().toString();
            match.setReference(ref);
        }

        match.setCompetition(competition);

        return matchRepository.save(match);
    }
}
