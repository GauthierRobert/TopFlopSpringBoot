package com.lhc.datamodel.entities.competition;

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

    @Column(unique=true)
    private String name;

    private String season;

    private String division;

    private Boolean withCommentTop;

    private Boolean withCommentFlop;

    private String topName;

    private String flopName;

    @OneToOne(mappedBy = "competition", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private ImageCompetition imageCompetition;

    private String password;
    @Transient
    private String confirmedPassword;

    private String creatorUsername;


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

    public static Competition competition(){
        return new Competition();
    }

    public static Competition competition(String name, String season, String division, String password, String confirmedPassword, String creatorUsername, String topName, String flopName, boolean withCommentTop, boolean withCommentFlop, List<Rule> rules) {
        Competition competition = new Competition(
                null,
                null,
                name,
                season,
                division,
                withCommentTop,
                withCommentFlop,
                topName,
                flopName,
                null,
                password,
                confirmedPassword,
                creatorUsername,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );
        competition.setRules(rules);
        return competition;
    }


    public Competition(Long id, String reference, String name, String season, String division, Boolean withCommentTop, Boolean withCommentFlop, String topName, String flopName, ImageCompetition imageCompetition, String password, String confirmedPassword, String creatorUsername, List<Match> matches, List<Training> trainings, List<UserCompetition> userCompetitions, List<Rule> rules) {
        this.id = id;
        this.reference = reference;
        this.name = name;
        this.season = season;
        this.division = division;
        this.withCommentTop = withCommentTop;
        this.withCommentFlop = withCommentFlop;
        this.topName = topName;
        this.flopName = flopName;
        this.imageCompetition = imageCompetition;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
        this.creatorUsername = creatorUsername;
        this.matches = matches;
        this.trainings = trainings;
        this.userCompetitions = userCompetitions;
        this.rules = rules;
    }

    public void setRules(List<Rule> rules) {
        for(Rule rule : rules){
            rule.setCompetition(this);
        }
        this.rules = rules;
    }




}
