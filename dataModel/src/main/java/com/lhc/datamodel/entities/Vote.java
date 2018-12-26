package com.lhc.datamodel.entities;

import com.lhc.datamodel.entities.rules.Rule;
import com.lhc.datamodel.entities.rules.VoteType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity(name = "Votes")
@Table(name = "Votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String reference;

    private String name;

    private Integer points;

    // 1 for first top vote
    // 2 for second top vote
    // ...
    // -1 for first flop vote
    // -2 for second flop vote
    private Integer indication;

    @ManyToOne
    private Ballot ballot;

    private VoteType voteType;

    public Vote applyRule(Rule rule){

        this.setPoints(rule.getPoints()*(int)Math.signum(indication));
        return this;

    }
}
