package com.lhc.datamodel.entities.competition;

import com.lhc.datamodel.enumeration.MatchStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Table(name = "Matches")
@Entity(name = "Matches")
public class Match implements Serializable {

    private static final String SEPARATOR = " § ";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String reference;

    @OneToMany(mappedBy = "match", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ballot> ballots;

    private String homeTeam;

    private Integer homeScore;

    private String awayTeam;

    private Integer awayScore;

    private MatchStatus status;

    private LocalDate date;

    private LocalTime time;

    private String creatorUsername;

    private String visitors;

    @ManyToOne
    private Competition competition;

    private String competition_ref;

    private Match() {
    }

    public Match(Long id, String reference, List<Ballot> ballots, String homeTeam, Integer homeScore, String awayTeam, Integer awayScore, MatchStatus status, LocalDate date, LocalTime time, String creatorUsername, String visitors, Competition competition, String competition_ref) {
        this.id = id;
        this.reference = reference;
        this.ballots = ballots;
        this.homeTeam = homeTeam;
        this.homeScore = homeScore;
        this.awayTeam = awayTeam;
        this.awayScore = awayScore;
        this.status = status;
        this.date = date;
        this.time = time;
        this.creatorUsername = creatorUsername;
        this.visitors = visitors;
        this.competition = competition;
        this.competition_ref = competition_ref;
    }

    public static Match match() {
        Match match = new Match();
        match.setDate(LocalDate.now());
        match.setStatus(MatchStatus.OPEN);
        return match;
    }

    public void close() {
        this.status = MatchStatus.CLOSED;
    }

    public void open() {
        this.status = MatchStatus.OPEN;
    }

    public void onHold() {
        this.status = MatchStatus.ON_HOLD;
    }

    public void addVisitors(String visitors){
        this.visitors = this.visitors + SEPARATOR + visitors;
    }
}
