package com.lhc.datamodel.entities.competition;


import com.lhc.datamodel.entities.security.User;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Table(name = "Ballots")
@Entity(name = "Ballots")
public class Ballot implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String reference;

    private String match_ref;

    private String competition_ref;

    private boolean counted;

    @Column(columnDefinition = "TEXT")
    private String commentTop;

    @Column(columnDefinition = "TEXT")
    private String commentFlop;

    @OneToMany(mappedBy="ballot", fetch= FetchType.EAGER, cascade = CascadeType.ALL )
    private List<Vote> votes;

    @ManyToOne
    private Match match;

    @ManyToOne
    private User user;

    private Ballot() {
        counted = false;
        votes = new ArrayList<>();
    }

    public static Ballot ballot(){
        return new Ballot();
    }

    public void addVote(Vote vote){
        votes.add(vote);
    }
}
