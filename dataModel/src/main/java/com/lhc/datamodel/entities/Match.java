package com.lhc.datamodel.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Table(name = "Matches")
@Entity(name = "Matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String reference;

    @OneToMany(mappedBy="match", fetch= FetchType.LAZY, cascade= CascadeType.ALL )
    private List<Ballot> ballots;

    private String homeTeam;

    private Integer homeScore;

    private String awayTeam;

    private Integer awayScore;
   
    private MatchStatus status;

    @ManyToOne
    private Competition competition;
    
    public void close(){
        this.status = MatchStatus.CLOSE;
    }

}
