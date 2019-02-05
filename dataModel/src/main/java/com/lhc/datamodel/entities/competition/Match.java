package com.lhc.datamodel.entities.competition;

import com.lhc.datamodel.enumeration.MatchStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Table(name = "Matches")
@Entity(name = "Matches")
public class Match implements Serializable  {

    public static final Match EMPTY_MATCH = new Match(){
        @Override
        public Boolean isExist() {
            return false;
        }
    };

    private Match() {
    }

    public Match(Long id, String reference, List<Ballot> ballots, String homeTeam, Integer homeScore, String awayTeam, Integer awayScore, MatchStatus status, LocalDate date, String creatorUsername, Competition competition, String competition_ref) {
        this.id = id;
        this.reference = reference;
        this.ballots = ballots;
        this.homeTeam = homeTeam;
        this.homeScore = homeScore;
        this.awayTeam = awayTeam;
        this.awayScore = awayScore;
        this.status = status;
        this.date = date;
        this.creatorUsername = creatorUsername;
        this.competition = competition;
        this.competition_ref = competition_ref;
    }

    public static Match match(){
        Match match = new Match();
        match.setDate(LocalDate.now());
        match.setStatus(MatchStatus.OPEN);
        return match;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String reference;

    @OneToMany(mappedBy="match", fetch= FetchType.LAZY, cascade= CascadeType.ALL )
    private List<Ballot> ballots;

    private String homeTeam;

    private Integer homeScore;

    private String awayTeam;

    private Integer awayScore;
   
    private MatchStatus status;
    
    private LocalDate date;

    private String creatorUsername;

    @ManyToOne
    private Competition competition;
    
    private String competition_ref;
    
    public void close(){
        this.status = MatchStatus.CLOSED;
    }

    public void open() {this.status = MatchStatus.OPEN; }

    public void onHold() {this.status = MatchStatus.ON_HOLD; }

    public Boolean isExist(){
        return true;
    }

}
