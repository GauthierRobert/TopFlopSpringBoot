package com.lhc.datamodel.entities.competition;

import com.lhc.datamodel.entities.SystemData;
import com.lhc.datamodel.entities.competition.embedded.MatchDetails;
import com.lhc.datamodel.entities.statistic.Statistic;
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

    @Embedded
    private SystemData systemData;

    @OneToMany(mappedBy = "match", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ballot> ballots;

    private MatchDetails details;

    private LocalDate date;

    private LocalTime time;

    @Enumerated(EnumType.STRING)
    private MatchStatus status;

    private String visitors;

    @ManyToOne
    private Competition competition;

    private String competition_ref;

    @OneToMany(mappedBy="match", fetch= FetchType.LAZY, cascade= CascadeType.ALL )
    private List<Statistic> statistics;

    private Match() {
    }

    private Match(Long id, List<Ballot> ballots, MatchDetails details, LocalDate date, LocalTime time, MatchStatus status, String creatorUsername, String visitors, Competition competition, String competition_ref, List<Statistic> statistics) {
        this.id = id;
        this.ballots = ballots;
        this.details = details;
        this.date = date;
        this.time = time;
        this.status = status;
        this.visitors = visitors;
        this.competition = competition;
        this.competition_ref = competition_ref;
        this.statistics = statistics;
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
        if(getVisitors()==null){
            this.setVisitors(visitors);
        } else {
            this.setVisitors(getVisitors() + visitors);
        }
    }
}
