package com.lhc.business.service.mapper;

import com.lhc.business.dto.Ballot;
import com.lhc.business.dto.Competition;
import com.lhc.business.dto.Match;
import com.lhc.business.dto.Vote;
import com.lhc.datamodel.entities.BallotRecord;
import com.lhc.datamodel.entities.CompetitionRecord;
import com.lhc.datamodel.entities.MatchRecord;
import com.lhc.datamodel.entities.VoteRecord;

public interface MapperHandler {

    Vote mapVote(VoteRecord voteRecord, Vote vote);
    Ballot mapBallot(BallotRecord ballotRecord, Ballot ballot);
    Match mapMatch(MatchRecord matchRecord, Match match);
    Competition mapCompetition(CompetitionRecord competitionRecord, Competition competition);

    VoteRecord mapVoteRecord(Vote vote, VoteRecord voteRecord);
    BallotRecord mapBallotRecord(Ballot ballot, BallotRecord ballotRecord);
    MatchRecord mapMatchRecord(Match match, MatchRecord matchRecord);
    CompetitionRecord mapCompetitionRecord(Competition competition, CompetitionRecord competitionRecord);

    RuleRecord mapRuleRecord(Rule rule, RuleRecord ruleRecord);
    Rule mapRule(RuleRecord ruleRecord, Rule rule);

}
