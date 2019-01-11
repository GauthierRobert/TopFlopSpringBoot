package com.lhc.datamodel.builder;

import com.lhc.datamodel.entities.Competition;
import com.lhc.datamodel.entities.rules.Rule;
import com.lhc.datamodel.enumeration.LabelType;

import java.util.ArrayList;
import java.util.List;

import static com.lhc.datamodel.entities.Competition.competition;

public class CompetitionBuilder {


    private Long id;
    private String name;
    private int season;
    private String division;
    private String password;
    private String confirmedPassword;
    private String creatorUsername;
    private List<Rule> rules = new ArrayList<>();

    public static CompetitionBuilder aCompetition(){
        return new CompetitionBuilder();
    }

    public CompetitionBuilder withName(String name){
        this.name = name;
        return this;
    }

    public CompetitionBuilder withSeason(int season){
        this.season = season;
        return this;
    }

    public CompetitionBuilder withDivision(String division){
        this.division = division;
        return this;
    }
    public CompetitionBuilder withPassword(String password){
        this.password = password;
        return this;
    }
    public CompetitionBuilder withConfirmedPassword(String confirmedPassword){
        this.confirmedPassword = confirmedPassword;
        return this;
    }
    public CompetitionBuilder withCreatorUsername(String creatorUsername){
        this.creatorUsername = creatorUsername;
        return this;
    }

    public RuleBuilder withRules(int numberOfTopVote, int numberOfFlopVote){

        return new RuleBuilder(numberOfTopVote, numberOfFlopVote);
    }

    public class RuleBuilder{

        private final CompetitionCompletion competitionCompletion = new CompetitionCompletion();

        public RuleBuilder(int numberOfTopVote, int numberOfFlopVote) {
            Rule ruleTop = new Rule(LabelType.NUMBER_VOTE_TOP.name(),numberOfTopVote, 0);
            Rule ruleFlop = new Rule(LabelType.NUMBER_VOTE_FLOP.name(), numberOfFlopVote, 0);
            CompetitionBuilder.this.rules.add(ruleTop);
            CompetitionBuilder.this.rules.add(ruleFlop);
        }

        public RuleBuilder withTopRules(Integer ... points){
            int i = 0;
            for (Integer point:points) {
                i++;
                Rule rule = new Rule(LabelType.POINT_VOTE.name(),point,i);
                CompetitionBuilder.this.rules.add(rule);
            }
            return this;
        }

        public RuleBuilder withFlopRules(Integer ... points){
            int i = 0;
            for (Integer point:points) {
                i++;
                Rule rule = new Rule(LabelType.POINT_VOTE.name(),point,-i);
                CompetitionBuilder.this.rules.add(rule);
            }
            return this;
        }

        public Competition build(){
            return competitionCompletion.build();
        }

    }


    public class CompetitionCompletion {

        private  CompetitionCompletion(){
        }

        public Competition build(){
            return competition(id, name, season, division, password, confirmedPassword, creatorUsername, rules);
        }
    }


}