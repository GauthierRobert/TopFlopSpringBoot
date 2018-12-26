package com.lhc.datamodel.entities.rules;

import com.lhc.datamodel.entities.Competition;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "Rules")
@Entity(name = "Rules")
@Getter
@Setter
public class Rule {

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




}
