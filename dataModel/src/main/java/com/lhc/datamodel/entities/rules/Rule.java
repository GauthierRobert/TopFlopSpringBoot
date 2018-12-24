package com.lhc.datamodel.entities.rules;

import com.lhc.datamodel.entities.CompetitionRecord;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "rules")
@Entity(name = "rules")
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




}
