package com.lhc.business.dto;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Match {

    private String reference;

    private List<Ballot> ballots;

    private String homeTeam;

    private Integer homeScore;

    private Integer awayScore;

    private String awayTeam;

    public Match() {
        this.ballots = new ArrayList<>();
    }
}
