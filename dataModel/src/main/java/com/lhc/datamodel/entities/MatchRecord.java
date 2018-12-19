package com.lhc.datamodel.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Table(name = "Matches")
@Entity(name = "Matches")
public class MatchRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String reference;

    @OneToMany(mappedBy="matchRecord", fetch= FetchType.LAZY, cascade= CascadeType.ALL )
    private List<BallotRecord> ballots;

    private String homeTeam;

    private Integer homeScore;

    private String awayTeam;

    private Integer awayScore;

    @ManyToOne
    private CompetitionRecord competitionRecord;

}
