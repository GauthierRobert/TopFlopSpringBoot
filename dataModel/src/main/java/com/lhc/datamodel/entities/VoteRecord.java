package com.lhc.datamodel.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity(name = "Votes")
@Table(name = "Votes")
public class VoteRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String reference;

    private String name;

    private Integer points;

    @ManyToOne
    private BallotRecord ballotRecord;
}
