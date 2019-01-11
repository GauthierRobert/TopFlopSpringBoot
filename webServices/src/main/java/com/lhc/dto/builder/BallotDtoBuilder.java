package com.lhc.dto.builder;

import com.lhc.datamodel.enumeration.LabelType;
import com.lhc.dto.BallotDto;
import com.lhc.dto.RuleDto;
import com.lhc.dto.VoteDto;

import java.util.ArrayList;
import java.util.List;

import static com.lhc.dto.BallotDto.ballotDto;

public class BallotDtoBuilder {

    private String match_ref;
    private String competition_ref;
    private List<VoteDto> voteDtos = new ArrayList<>();
    private List<RuleDto> ruleDtos = new ArrayList<>();

    public static BallotDtoBuilder aBallotDto(){
        return new BallotDtoBuilder();
    }

    public BallotDtoBuilder aBallotDtoWithRules(List<RuleDto> ruleDtos){
        return aBallotDto().withRuleDtos(ruleDtos);
    }

    private BallotDtoBuilder withRuleDtos(List<RuleDto> ruleDtos) {
        this.ruleDtos = ruleDtos;
        return this;
    }

    public BallotDtoBuilder withMatch_ref(String match_ref){
        this.match_ref = match_ref;
        return this;
    }

    public BallotDtoBuilder withCompetition_ref(String competition_ref){
        this.match_ref = competition_ref;
        return this;
    }

    public VoteDtoBuilder withVotesDtos(){

        return new VoteDtoBuilder();
    }

    public class VoteDtoBuilder {

        private final BallotDtoBuilder.BallotDtoCompletion ballotCompletion = new BallotDtoBuilder.BallotDtoCompletion();

        public VoteDtoBuilder() {

        }

        public VoteDtoBuilder addTopVote(String ... names){
            int i = 0;
            for (String name : names) {
                RuleDto RuleDto = ruleDtos.get(i);
                VoteDto voteDto  = new VoteDto();
                BallotDtoBuilder.this.voteDtos.add(voteDto);
                i++;
            }
            return this;
        }

        public VoteDtoBuilder withFlopVote(String ... names){
            int i = 0;
            for (String name : names) {
                RuleDto RuleDto = ruleDtos.get(i);
                VoteDto voteDto  = new VoteDto();
                BallotDtoBuilder.this.voteDtos.add(voteDto);
                i++;
            }
            return this;
        }

        public BallotDto build(){
            return ballotCompletion.build();
        }

    }


    public class BallotDtoCompletion {

        private BallotDtoCompletion(){
        }

        public BallotDto build(){
            return ballotDto(match_ref, competition_ref, voteDtos, ruleDtos);
        }
    }


}
