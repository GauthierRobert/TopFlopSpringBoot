package com.lhc.dto.builder;

import com.lhc.datamodel.entities.competition.embedded.MatchDetails;
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

    public static MatchDtoBuilder aMatchDto() {
        return new MatchDtoBuilder();
    }

    public MatchDtoBuilder createBy(String creatorUsername) {
        this.creatorUsername = creatorUsername;
        return this;
    }

    public MatchDtoBuilder withSpectators(List<String> spectators) {
        this.spectators = spectators;
        return this;
    }


    public HomeTeam inCompetiton(String competition_ref) {
        this.competition_ref = competition_ref;
        return new HomeTeam();
    }

    public class HomeTeam {

        HomeTeam(String awayTeam) {
            MatchDtoBuilder.this.homeTeam = homeTeam;
        }

        HomeTeam() {

        }

        public HomeTeam withHomeTeam(String homeTeam) {
            MatchDtoBuilder.this.homeTeam = homeTeam;
            return this;
        }

        public AwayTeam finallyScore(Integer scoreHome) {
            MatchDtoBuilder.this.scoreHome = scoreHome;
            return new AwayTeam();
        }

    }

    public class AwayTeam {

        private final MatchDtoCompletion matchDtoCompletion = new MatchDtoCompletion();


        AwayTeam(String awayTeam) {
            MatchDtoBuilder.this.awayTeam = awayTeam;
        }

        AwayTeam() {

        }

        public AwayTeam withAwayTeam(String awayTeam) {
            MatchDtoBuilder.this.awayTeam = awayTeam;
            return this;
        }

        public AwayTeam finallyScore(Integer scoreAway) {
            MatchDtoBuilder.this.scoreAway = scoreAway;
            return this;
        }

        public MatchDto build() {
            return matchDtoCompletion.build();
        }

    }


    public class MatchDtoCompletion {

        private MatchDtoCompletion() {
        }

        public MatchDto build() {
            return MatchDto.matchDto(competition_ref,
                    MatchDetails.matchDetails(homeTeam, scoreHome, awayTeam, scoreAway),
                    creatorUsername, spectators);
        }
    }


}
