package com.lhc.datamodel.entities;


import com.lhc.datamodel.entities.security.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Table(name = "Ballots")
@Entity(name = "Ballots")
public class BallotRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String reference;

    @OneToMany(mappedBy="ballotRecord", fetch= FetchType.LAZY, cascade= CascadeType.ALL )
    private List<VoteRecord> votes;

    @ManyToOne
    private MatchRecord matchRecord;

    @ManyToOne
    private User user;



}
