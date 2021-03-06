package com.lhc.datamodel.entities.rules;

import com.lhc.datamodel.entities.competition.Competition;
import com.lhc.datamodel.enumeration.Label;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "Rules")
@Entity(name = "Rules")
@Getter
@Setter
public class Rule implements Serializable {

    public static final Rule EMPTY_RULE = new Rule(){
        @Override
        public Boolean isExist() {
            return false;
        }
    };


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private Label label;

    private Integer points;

    @ManyToOne
    private Competition competition;

    // 1 for first top vote
    // 2 for second top vote
    // ...
    // -1 for first flop vote
    // -2 for second flop vote
    private Integer indication;

    public Boolean isExist(){
        return true;
    }

    private Rule() {

    }

    private Rule(Long id, String description, Label label, Integer points, Competition competition, Integer indication) {
        this.id = id;
        this.description = description;
        this.label = label;
        this.points = points;
        this.competition = competition;
        this.indication = indication;
    }

    public static Rule rule(Long id, String description, Label label, Integer points, Competition competition, Integer indication){
        return new Rule(id, description, label, points, competition, indication);
    }

    public static Rule rule(){
        return new Rule();
    }

    public static Rule rule(Label label, Integer points, Integer indication) {
        return new Rule(null, null, label, points, null, indication);
    }




    }
