package com.lhc.datamodel.entities;

import com.lhc.datamodel.enumeration.MatchStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
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
    
    private Date date;

    @ManyToOne
    private Competition competition;
    
    private String competition_ref;
    
    public void close(){
        this.status = MatchStatus.CLOSE;
    }

}
