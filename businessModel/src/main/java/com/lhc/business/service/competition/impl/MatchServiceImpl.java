package com.lhc.business.service.competition.impl;

import com.lhc.business.service.competition.BallotService;
import com.lhc.business.service.competition.CompetitionService;
import com.lhc.business.service.competition.MatchService;
import com.lhc.datamodel.entities.competition.Ballot;
import com.lhc.datamodel.entities.competition.Competition;
import com.lhc.datamodel.entities.competition.Match;
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
    private BallotService ballotService;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository, CompetitionService competitionService, BallotService ballotService) {
        this.matchRepository = matchRepository;
        this.competitionService = competitionService;
        this.ballotService = ballotService;
    }

    @Override
    public Match findMatchByReference(String ref) {

        return matchRepository.findByReference(ref);

    }

    @Override
    public Long deleteMatchByReference(String reference) {
        return matchRepository.deleteByReference(reference);
    }

    @Override
    public Match findMatchByReferenceAndHisBallots(String match_ref) {

        Match match = matchRepository.findByReference(match_ref);
        List<Ballot> ballots = ballotService.findAllBallotsByMatchReference(match_ref);
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

        if (match.getReference() !=null) {
            Match matchInDb = matchRepository.findByReference(match.getReference());
            if(matchInDb != null) {
                updateData(match, matchInDb);
                return matchRepository.save(matchInDb);
            }
        } else {
            match.setReference(UUID.randomUUID().toString());
        }

        Competition competition = competitionService.findByReference(match.getCompetition_ref());
        match.setCompetition(competition);

        return matchRepository.save(match);
    }

    private void updateData(Match match, Match matchInDb)    {
        matchInDb.setDetails(match.getDetails());
    }

    @Override
    public Match close(String match_ref){
        
        Match match = findMatchByReference(match_ref);
        match.close();
        return matchRepository.save(match);
        
    }

    @Override
    public Match open(String match_ref){

        Match match = findMatchByReference(match_ref);
        match.open();
        return matchRepository.save(match);

    }
}
