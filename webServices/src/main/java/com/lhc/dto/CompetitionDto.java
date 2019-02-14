package com.lhc.dto;


import com.lhc.datamodel.entities.competition.embedded.CompetitionDetails;
import com.lhc.datamodel.entities.competition.embedded.TopFlopDetails;
import com.lhc.datamodel.entities.competition.embedded.DataName;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CompetitionDto {

    private String reference;

    private String password;

    private String confirmedPassword;

    private String creatorUsername;

    private String imageAsBase64;

    private CompetitionDetails details;

    private TopFlopDetails topFlopDetails;

    private DataName dataName;

    private List<MatchDto> matchDtos;

    private List<RuleDto> ruleDtos;

    public CompetitionDto() {
        this.ruleDtos = new ArrayList<>();
        this.matchDtos = new ArrayList<>();
    }

    public CompetitionDto(String reference) {
        this.reference = reference;
    }

    public CompetitionDto(String reference, String password, String confirmedPassword, String creatorUsername, String imageAsBase64, CompetitionDetails details, TopFlopDetails topFlopDetails, DataName dataName, List<MatchDto> matchDtos, List<RuleDto> ruleDtos) {
        this.reference = reference;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
        this.creatorUsername = creatorUsername;
        this.imageAsBase64 = imageAsBase64;
        this.details = details;
        this.topFlopDetails = topFlopDetails;
        this.dataName = dataName;
        this.matchDtos = matchDtos;
        this.ruleDtos = ruleDtos;
    }

    public static CompetitionDto competitionDto(CompetitionDetails details, TopFlopDetails topFlopDetails, DataName dataName, String password, String confirmedPassword, String creatorUsername, String imageAsBase64, List<RuleDto> ruleDtos) {
        return new CompetitionDto(
                null,
                password,
                confirmedPassword,
                creatorUsername,
                imageAsBase64,
                details,
                topFlopDetails,
                dataName,
                new ArrayList<>(),
                ruleDtos);

    }

    public static CompetitionDto competitionDto(String reference) {
        return new CompetitionDto(reference);

    }
}
