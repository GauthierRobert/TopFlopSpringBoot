package com.lhc.business.service.mapper;

import com.lhc.business.dto.*;
import com.lhc.datamodel.entities.BallotRecord;
import com.lhc.datamodel.entities.CompetitionRecord;
import com.lhc.datamodel.entities.MatchRecord;
import com.lhc.datamodel.entities.VoteRecord;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapperHandlerImpl implements MapperHandler {


    @Override
    public Vote mapVote(VoteRecord voteRecord, Vote vote) {

        if (vote ==null){
            vote = new Vote();
        }

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(VoteRecord.class, Vote.class)
                .field("reference", "reference")
                .field("name", "name")
                .field("points", "points")
                .register();

        MapperFacade mapper = mapperFactory.getMapperFacade();

        mapper.map(voteRecord, vote);

        return vote;
    }

    @Override
    public Ballot mapBallot(BallotRecord ballotRecord, Ballot ballot) {

        if (ballot ==null){
            ballot = new Ballot();
        }

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(BallotRecord.class, Ballot.class)
                .field("reference", "reference")
                .register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        mapper.map(ballotRecord, ballot);


        return ballot;

    }

    @Override
    public Match mapMatch(MatchRecord matchRecord, Match match) {

        if (match ==null){
            match = new Match();
        }

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(MatchRecord.class, Match.class)
                .field("homeTeam", "homeTeam")
                .field("homeScore", "homeScore")
                .field("awayTeam", "awayTeam")
                .field("awayScore", "awayScore")
                .field("reference", "reference")
                .register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        mapper.map(matchRecord, match);

        return match;

    }

    @Override
    public Competition mapCompetition(CompetitionRecord competitionRecord, Competition competition) {
        if (competition ==null){
            competition = new Competition();
        }

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(CompetitionRecord.class, Competition.class)
                .field("reference", "reference")
                .field("name", "name")
                .field("password", "password")
                .register();

        MapperFacade mapper = mapperFactory.getMapperFacade();

        mapper.map(competitionRecord, competition);

        return competition;

    }

    @Override
    public VoteRecord mapVoteRecord(Vote vote, VoteRecord voteRecord) {

        if (voteRecord == null) {
            voteRecord = new VoteRecord();
        }

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Vote.class, VoteRecord.class)
                .field("reference", "reference")
                .field("name", "name")
                .field("points", "points")
                .register();

        MapperFacade mapper = mapperFactory.getMapperFacade();
        mapper.map(vote, voteRecord);

        return voteRecord;
    }

    @Override
    public BallotRecord mapBallotRecord(Ballot ballot, BallotRecord ballotRecord) {

        if (ballotRecord ==null){
            ballotRecord = new BallotRecord();
        }

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Ballot.class, BallotRecord.class)
                .field("reference", "reference")
                .register();

        MapperFacade mapper = mapperFactory.getMapperFacade();

        mapper.map(ballot, ballotRecord);

        return ballotRecord;
    }

    @Override
    public MatchRecord mapMatchRecord(Match match, MatchRecord matchRecord) {

        if (matchRecord ==null){
            matchRecord = new MatchRecord();
        }

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Match.class, MatchRecord.class)
                .field("homeTeam", "homeTeam")
                .field("homeScore", "homeScore")
                .field("awayTeam", "awayTeam")
                .field("awayScore", "awayScore")
                .field("reference", "reference")
                .register();
        MapperFacade mapper = mapperFactory.getMapperFacade();

        mapper.map(match, matchRecord);

        return matchRecord;

    }

    @Override
    public CompetitionRecord mapCompetitionRecord(Competition competition, CompetitionRecord competitionRecord) {

        if (competitionRecord ==null){
            competitionRecord = new CompetitionRecord();
        }

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Competition.class, CompetitionRecord.class)
                .field("reference", "reference")
                .field("name", "name")
                .field("password", "password")
                .register();

        MapperFacade mapper = mapperFactory.getMapperFacade();

        mapper.map(competition, competitionRecord);

        return competitionRecord;

    }


    @Override
    public RuleRecord mapRuleRecord(Rule rule, RuleRecord ruleRecord){

        if (ruleRecord ==null){
            ruleRecord = new RuleRecord();
        }

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Rule.class, RuleRecord.class)
                .field("description", "description")
                .field("label", "label")
                .field("points", "points")
                .register();

        MapperFacade mapper = mapperFactory.getMapperFacade();

        mapper.map(rule, ruleRecord);

        return ruleRecord;

    }

    @Override
    public Rule mapRule(RuleRecord ruleRecord, Rule rule){

        if (rule ==null){
            rule = new Rule();
        }

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(RuleRecord.class, Rule.class)
                .field("description", "description")
                .field("label", "label")
                .field("points", "points")
                .register();

        MapperFacade mapper = mapperFactory.getMapperFacade();

        mapper.map(ruleRecord, rule);

        return rule;
    }

}
