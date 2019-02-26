package com.lhc.datamodel.builder;

import com.lhc.datamodel.entities.competition.Competition;
import com.lhc.datamodel.entities.rules.Rule;
import com.lhc.datamodel.enumeration.Label;
import com.lhc.datamodel.enumeration.Sport;

import java.util.ArrayList;
import java.util.List;

import static com.lhc.datamodel.entities.competition.Competition.competition;
import static com.lhc.datamodel.entities.competition.embedded.CompetitionDetails.competitionDetails;
import static com.lhc.datamodel.entities.competition.embedded.TopFlopDetails.topFlopDetails;
import static com.lhc.datamodel.entities.rules.Rule.rule;
import static com.lhc.datamodel.entities.competition.embedded.DataName.dataName;

public class CompetitionBuilder {


    private Long id;

    private String name;
    private String season;
    private String division;
    private Sport sport;

    private String password;
    private String confirmedPassword;
    private String creatorUsername;

    private String dataName_1;
    private String dataName_2;
    private String dataName_3;
    private String dataName_4;
    private String dataName_5;

    private boolean withCommentTop;
    private boolean withCommentFlop;
    private boolean withValidationTop;
    private boolean withValidationFlop;
    private String topName;
    private String flopName;

    private List<Rule> rules = new ArrayList<>();



    private static CompetitionBuilder aCompetition(){
        return new CompetitionBuilder();
    }

    public static CompetitionBuilder aCompetitionCreatedBy(String creatorUsername){
        return  aCompetition().withCreatorUsername(creatorUsername);
    }

    private CompetitionBuilder withCreatorUsername(String creatorUsername){
        this.creatorUsername = creatorUsername;
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

    public CompetitionDetailsBuilder finallySport(Sport sport) {
        return new CompetitionDetailsBuilder(sport);
    }

    public class CompetitionDetailsBuilder{

        CompetitionDetailsBuilder(Sport sport) {
            CompetitionBuilder.this.sport = sport;
        }

        public CompetitionDetailsBuilder withName(String name){
            CompetitionBuilder.this.name = name;
            return this;
        }

        public CompetitionDetailsBuilder withSeason(String season){
            CompetitionBuilder.this.season = season;
            return this;
        }

        public CompetitionDetailsBuilder withDivision(String division){
            CompetitionBuilder.this.division = division;
            return this;}

        public TopFlopDetailsBuilder finallyStatisicsName(String _1, String _2, String _3, String _4, String _5){
            return new TopFlopDetailsBuilder(_1,_2,_3,_4,_5);
        }

    }

    public class TopFlopDetailsBuilder{

        TopFlopDetailsBuilder(String _1, String _2, String _3, String _4, String _5) {
            CompetitionBuilder.this.dataName_1 = _1;
            CompetitionBuilder.this.dataName_2 = _2;
            CompetitionBuilder.this.dataName_3 = _3;
            CompetitionBuilder.this.dataName_4 = _4;
            CompetitionBuilder.this.dataName_5 = _5;
        }

        public TopFlopDetailsBuilder withComments(boolean withCommentTop, boolean withCommentFlop){
            CompetitionBuilder.this.withCommentTop = withCommentTop;
            CompetitionBuilder.this.withCommentFlop = withCommentFlop;
            return this;
        }

        public TopFlopDetailsBuilder withTopFlopName(String topName, String flopName){
            CompetitionBuilder.this.topName = topName;
            CompetitionBuilder.this.flopName = flopName;
            return this;
        }

        public TopFlopDetailsBuilder withValidation(boolean withValidationTop, boolean withValidationFlop){
            CompetitionBuilder.this.withValidationTop = withValidationTop;
            CompetitionBuilder.this.withValidationFlop = withValidationFlop;
            return this;
        }

        public RuleBuilder finallyRules(int numberOfTopVote, int numberOfFlopVote){
            return new RuleBuilder(numberOfTopVote, numberOfFlopVote);
        }
    }


    public class RuleBuilder{

        private final CompetitionCompletion competitionCompletion = new CompetitionCompletion();

        RuleBuilder(int numberOfTopVote, int numberOfFlopVote) {
            Rule ruleTop = rule(Label.NUMBER_VOTE_TOP,numberOfTopVote, 0);
            Rule ruleFlop = rule(Label.NUMBER_VOTE_FLOP, numberOfFlopVote, 0);
            CompetitionBuilder.this.rules.add(ruleTop);
            CompetitionBuilder.this.rules.add(ruleFlop);
        }

        public RuleBuilder withTopRules(Integer ... points){
            int i = 0;
            for (Integer point:points) {
                i++;
                Rule rule = rule(Label.POINT_VOTE,point,i);
                CompetitionBuilder.this.rules.add(rule);
            }
            return this;
        }

        public RuleBuilder withFlopRules(Integer ... points){
            int i = 0;
            for (Integer point:points) {
                i++;
                Rule rule = rule(Label.POINT_VOTE,point,-i);
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
            return competition(
                    competitionDetails(name, season, division,sport),
                    topFlopDetails(withCommentTop, withCommentFlop, withValidationTop, withValidationFlop, topName, flopName),
                    dataName(dataName_1, dataName_2, dataName_3, dataName_4, dataName_5),
                    password, confirmedPassword, creatorUsername,rules);
        }
    }


}
