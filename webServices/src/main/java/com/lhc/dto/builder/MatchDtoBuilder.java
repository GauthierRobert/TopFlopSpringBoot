package com.lhc.dto.builder;

import com.lhc.dto.MatchDto;

import java.util.List;

public class MatchDtoBuilder {

    private String competition_ref;
    private String creatorUsername;
    private String homeTeam;
    private String awayTeam;
    private Integer scoreHome;
    private Integer scoreAway;
    private List<String> spectators;

    public static MatchDtoBuilder aMatchDto(){
        return new MatchDtoBuilder();
    }

    public MatchDtoBuilder withHomeTeam(String homeTeam){
        this.homeTeam = homeTeam;
        return this;
    }

    public MatchDtoBuilder withScore(Integer scoreHome){
        this.scoreHome = scoreHome;
        return this;
    }


    public MatchDtoBuilder createBy(String creatorUsername){
        this.creatorUsername = creatorUsername;
        return this;
    }

    public MatchDtoBuilder withSpectators(List<String> spectators) {
        this.spectators = spectators;
        return this;
    }


    public MatchDtoBuilder inCompetiton(String competition_ref){
        this.competition_ref = competition_ref;
        return this;
    }


    public AgainstTeam againstTeam(String awayTeam){
        return new AgainstTeam(awayTeam);
    }



    public class AgainstTeam{

        private final MatchDtoCompletion matchDtoCompletion = new MatchDtoCompletion();


        public AgainstTeam(String awayTeam) {
            MatchDtoBuilder.this.awayTeam = awayTeam;
        }

        public AgainstTeam withScore(Integer scoreAway){
            MatchDtoBuilder.this.scoreAway = scoreAway;
            return this;
        }

        public MatchDto build(){
            return matchDtoCompletion.build();
        }

    }


    public class MatchDtoCompletion {

        private MatchDtoCompletion(){
        }

        public MatchDto build(){
            return MatchDto.matchDto(competition_ref, homeTeam,scoreHome, scoreAway, awayTeam, creatorUsername, spectators);
        }
    }





}
