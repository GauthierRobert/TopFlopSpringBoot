package com.lhc.datamodel.entities;

import com.lhc.datamodel.entities.rules.Rule;
import com.lhc.datamodel.enumeration.VoteType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@Entity(name = "Votes")
@Table(name = "Votes")
public class Vote  implements Serializable {

    public static final Vote EMPTY_VOTE = new Vote(){
        @Override
        public Boolean isExist() {
            return false;
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }
    };

    private Vote(){

    }

    public static Vote vote(){
        return new Vote();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String reference;
    
    private String ballot_ref;
    
    private String match_ref;
    
    private String competition_ref;

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

    public Boolean isExist(){
        return true;
    }
}
