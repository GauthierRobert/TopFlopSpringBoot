package com.lhc.datamodel.entities.competition;

import com.lhc.datamodel.entities.competition.embedded.CompetitionDetails;
import com.lhc.datamodel.entities.competition.embedded.DataName;
import com.lhc.datamodel.entities.competition.embedded.TopFlopDetails;
import com.lhc.datamodel.entities.competition.manyToMany.UserCompetition;
import com.lhc.datamodel.entities.image.ImageCompetition;
import com.lhc.datamodel.entities.rules.Rule;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "Competitions")
@Table(name = "Competitions")
public class Competition implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String reference;

    private String password;
    @Transient
    private String confirmedPassword;

    private String creatorUsername;

    @Embedded
    private CompetitionDetails details;

    @Embedded
    private TopFlopDetails topFlopDetails;

    @Embedded
    private DataName dataName;

    @OneToOne(mappedBy = "competition", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private ImageCompetition imageCompetition;

    @OneToMany(mappedBy="competition", fetch= FetchType.LAZY, cascade= CascadeType.ALL )
    private List<Match> matches;

    @OneToMany(mappedBy="competition", fetch= FetchType.LAZY, cascade= CascadeType.ALL )
    private List<Training> trainings;

    @OneToMany(mappedBy = "competition")
    private List<UserCompetition> userCompetitions;

    @OneToMany(mappedBy="competition", fetch= FetchType.EAGER, cascade= CascadeType.ALL )
    private List<Rule> rules;

    private Competition() {
        this.matches = new ArrayList<>();
        this.userCompetitions = new ArrayList<>();
        this.rules = new ArrayList<>();
        this.trainings = new ArrayList<>();
    }

    private Competition(Long id, String reference, CompetitionDetails details, TopFlopDetails topFlopDetails, ImageCompetition imageCompetition, String password, String confirmedPassword, String creatorUsername, DataName dataName, List<Match> matches, List<Training> trainings, List<UserCompetition> userCompetitions, List<Rule> rules) {
        this.id = id;
        this.reference = reference;
        this.details = details;
        this.topFlopDetails = topFlopDetails;
        this.imageCompetition = imageCompetition;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
        this.creatorUsername = creatorUsername;
        this.dataName = dataName;
        this.matches = matches;
        this.trainings = trainings;
        this.userCompetitions = userCompetitions;
        this.rules = rules;
    }

    public static Competition competition(){
        return new Competition();
    }

    public static Competition competition(CompetitionDetails details, TopFlopDetails topFlopDetails,  DataName dataName, String password, String confirmedPassword, String creatorUsername, List<Rule> rules) {
        Competition competition = new Competition(
                null,
                null,
                details,
                topFlopDetails,
                null,
                password,
                confirmedPassword,
                creatorUsername,
                dataName,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );
        competition.setRules(rules);
        return competition;
    }

    public void setRules(List<Rule> rules) {
        for(Rule rule : rules){
            rule.setCompetition(this);
        }
        this.rules = rules;
    }




}
