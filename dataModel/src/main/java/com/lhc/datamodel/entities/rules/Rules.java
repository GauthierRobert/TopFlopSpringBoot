package com.lhc.datamodel.entities.rules;

import com.lhc.datamodel.entities.CompetitionRecord;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "rules")
@Entity(name = "rules")
@Getter
@Setter
public class Rules {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String label;

    Integer points;

    @ManyToOne
    CompetitionRecord competitionRecord;


}
