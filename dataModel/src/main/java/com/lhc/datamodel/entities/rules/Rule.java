package com.lhc.datamodel.entities.rules;

import com.lhc.datamodel.entities.Competition;
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

    public Rule(String label, Integer points, Integer indication) {
        this(null, null,label, points, null, indication);
    }

    public Rule() {

    }

    public Rule(Long id, String description, String label, Integer points, Competition competition, Integer indication) {
        this.id = id;
        this.description = description;
        this.label = label;
        this.points = points;
        this.competition = competition;
        this.indication = indication;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    private String label;

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

}
