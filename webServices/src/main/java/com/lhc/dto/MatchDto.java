package com.lhc.dto;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MatchDto {

    private String reference;

    private List<BallotDto> ballotDtos;

    private String homeTeam;

    private Integer homeScore;

    private Integer awayScore;

    private String awayTeam;

    public MatchDto() {
        this.ballotDtos = new ArrayList<>();
    }
}
