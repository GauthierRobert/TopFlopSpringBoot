package com.lhc.business.service.competition.impl;

import com.lhc.business.service.competition.BallotService;
import com.lhc.business.service.competition.CompetitionService;
import com.lhc.business.service.competition.MatchService;
import com.lhc.datamodel.entities.SystemData;
import com.lhc.datamodel.entities.competition.Ballot;
import com.lhc.datamodel.entities.competition.Competition;
import com.lhc.datamodel.entities.competition.Match;
import com.lhc.datamodel.repository.competition.BallotRepository;
import com.lhc.datamodel.repository.competition.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class MatchServiceImpl implements MatchService {

    private MatchRepository matchRepository;
    private CompetitionService competitionService;
    private BallotRepository ballotRepository;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository, CompetitionService competitionService, BallotRepository ballotRepository) {
        this.matchRepository = matchRepository;
        this.competitionService = competitionService;
        this.ballotRepository = ballotRepository;
    }

    @Override
    public Match findMatchByReference(String ref) {

        return matchRepository.findByReference(ref);

    }

    @Override
    public void deleteMatchByReference(String reference) {
        matchRepository.deleteByReference(reference);
    }

    @Override
    public Match findMatchByReferenceAndHisBallots(String match_ref) {

        Match match = matchRepository.findByReference(match_ref);
        List<Ballot> ballots = ballotRepository.findAllByMatchReference(match_ref);
        match.setBallots(ballots);

        return match;
    }

    @Override
    public Match addVisitors(String match_ref, String visitors) {
        Match match = findMatchByReference(match_ref);
        match.addVisitors(visitors);
        return matchRepository.save(match);
    }

    @Override
    public List<Match> findAllMatchesByCompetitionReference(String competition_ref) {

        return matchRepository.findAllByCompetitionReference(competition_ref);
    }


    @Override
    @Transactional
    public Match saveOrUpdate(Match match) {

        SystemData systemData = SystemData.systemData(UUID.randomUUID().toString(), match.getSystemData().getCreatedBy());
        if (match.getSystemData().getReference() != null) {
            Match matchInDb = matchRepository.findByReference(match.getSystemData().getReference());
            if (matchInDb != null) {
                updateData(match, matchInDb);
                match.setSystemData(SystemData.updated(systemData, systemData.getModifiedBy()));
                return matchRepository.save(matchInDb);
            }
        }

        Competition competition = competitionService.findByReference(match.getCompetition_ref());
        match.setCompetition(competition);
        match.setSystemData(systemData);
        return matchRepository.save(match);
    }

    private void updateData(Match match, Match matchInDb) {
        matchInDb.setDetails(match.getDetails());
    }

    @Override
    public Match close(String match_ref) {

        Match match = findMatchByReference(match_ref);
        match.close();
        return matchRepository.save(match);

    }

    @Override
    public Match open(String match_ref) {

        Match match = findMatchByReference(match_ref);
        match.open();
        return matchRepository.save(match);

    }
}
