package com.lhc.datamodel.entities.competition.embedded;

import com.lhc.datamodel.enumeration.Sport;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Data
public class CompetitionDetails {

    @Column(unique=true)
    private String name;

    private String season;

    private String division;

    @Enumerated(EnumType.STRING)
    private Sport sport;

    public CompetitionDetails(String name, String season, String division, Sport sport) {
        this.name = name;
        this.season = season;
        this.division = division;
        this.sport = sport;
    }

    public static CompetitionDetails competitionDetails(String name, String season, String division, Sport sport){
        return new CompetitionDetails(name, season, division, sport);
    }
}
