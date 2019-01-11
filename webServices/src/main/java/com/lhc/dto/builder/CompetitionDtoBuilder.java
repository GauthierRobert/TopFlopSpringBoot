package com.lhc.dto.builder;

import com.lhc.datamodel.enumeration.LabelType;
import com.lhc.dto.CompetitionDto;
import com.lhc.dto.RuleDto;

import java.util.ArrayList;
import java.util.List;

import static com.lhc.dto.CompetitionDto.competitionDto;

public class CompetitionDtoBuilder {

    private String name;
    private int season;
    private String division;
    private String password;
    private String confirmedPassword;
    private String creatorUsername;
    private List<RuleDto> RuleDtos = new ArrayList<>();

    public static CompetitionDtoBuilder aCompetitionDto(){
        return new CompetitionDtoBuilder();
    }

    public CompetitionDtoBuilder withName(String name){
        this.name = name;
        return this;
    }

    public CompetitionDtoBuilder withSeason(int season){
        this.season = season;
        return this;
    }

    public CompetitionDtoBuilder withDivision(String division){
        this.division = division;
        return this;
    }
    public CompetitionDtoBuilder withPassword(String password){
        this.password = password;
        return this;
    }
    public CompetitionDtoBuilder withConfirmedPassword(String confirmedPassword){
        this.confirmedPassword = confirmedPassword;
        return this;
    }
    public CompetitionDtoBuilder withCreatorUsername(String creatorUsername){
        this.creatorUsername = creatorUsername;
        return this;
    }

    public RuleDtoDtoBuilder withRuleDtos(int numberOfTopVote, int numberOfFlopVote){

        return new RuleDtoDtoBuilder(numberOfTopVote, numberOfFlopVote);
    }

    public class RuleDtoDtoBuilder {

        private final CompetitionDtoCompletion competitionCompletion = new CompetitionDtoCompletion();

        public RuleDtoDtoBuilder(int numberOfTopVote, int numberOfFlopVote) {
            RuleDto RuleDtoTop = new RuleDto(LabelType.NUMBER_VOTE_TOP.name(),numberOfTopVote, 0);
            RuleDto RuleDtoFlop = new RuleDto(LabelType.NUMBER_VOTE_FLOP.name(), numberOfFlopVote, 0);
            CompetitionDtoBuilder.this.RuleDtos.add(RuleDtoTop);
            CompetitionDtoBuilder.this.RuleDtos.add(RuleDtoFlop);
        }

        public RuleDtoDtoBuilder withTopRuleDtos(Integer ... points){
            int i = 0;
            for (Integer point:points) {
                i++;
                RuleDto RuleDto = new RuleDto(LabelType.POINT_VOTE.name(),point,i);
                CompetitionDtoBuilder.this.RuleDtos.add(RuleDto);
            }
            return this;
        }

        public RuleDtoDtoBuilder withFlopRuleDtos(Integer ... points){
            int i = 0;
            for (Integer point:points) {
                i++;
                RuleDto RuleDto = new RuleDto(LabelType.POINT_VOTE.name(),point,-i);
                CompetitionDtoBuilder.this.RuleDtos.add(RuleDto);
            }
            return this;
        }

        public CompetitionDto build(){
            return competitionCompletion.build();
        }

    }


    public class CompetitionDtoCompletion {

        private CompetitionDtoCompletion(){
        }

        public CompetitionDto build(){
            return competitionDto(name, season, division, password, confirmedPassword, creatorUsername, RuleDtos);
        }
    }


}
