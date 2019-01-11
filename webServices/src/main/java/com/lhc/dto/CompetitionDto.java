package com.lhc.dto;


import com.lhc.datamodel.entities.Competition;
import com.lhc.datamodel.entities.Match;
import com.lhc.datamodel.entities.rules.Rule;
import com.lhc.datamodel.entities.security.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CompetitionDto {

    private String reference;

    private String name;

    private String password;

    private String confirmedPassword;

    private String creatorUsername;

    private String division;

    private String season;

    private List<MatchDto> matchDtos;

    private List<RuleDto> ruleDtos;

    public CompetitionDto() {
        this.ruleDtos = new ArrayList<>();
        this.matchDtos = new ArrayList<>();
    }

    public static CompetitionDto competitionDto(String name, int season, String division,  String password, String confirmedPassword, String creatorUsername, List<RuleDto> ruleDtos) {
        return new CompetitionDto(
                name,
                name,
                password,
                confirmedPassword,
                creatorUsername,
                division,
                createSeason(season),
                new ArrayList<>() ,
                ruleDtos);

    }

    private static String createSeason(int season) {

        return season + " - " + (season + 1);

    }

    private CompetitionDto(String reference, String name, String password, String confirmedPassword, String creatorUsername, String division, String season, List<MatchDto> matchDtos, List<RuleDto> ruleDtos) {
        this.reference = reference;
        this.name = name;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
        this.creatorUsername = creatorUsername;
        this.division = division;
        this.season = season;
        this.matchDtos = matchDtos;
        this.ruleDtos = ruleDtos;
    }

}
