package com.lhc.datamodel.entities.competition;

import com.lhc.datamodel.entities.SystemData;
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

import static com.lhc.datamodel.entities.SystemData.systemData;

@Getter
@Setter
@Entity(name = "Competitions")
@Table(name = "Competitions")
public class Competition implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private SystemData systemData;

    private String password;
    @Transient
    private String confirmedPassword;

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

    private Competition(Long id, SystemData systemData, String password, String confirmedPassword, CompetitionDetails details, TopFlopDetails topFlopDetails, DataName dataName, ImageCompetition imageCompetition, List<Match> matches, List<Training> trainings, List<UserCompetition> userCompetitions, List<Rule> rules) {
        this.id = id;
        this.systemData = systemData;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
        this.details = details;
        this.topFlopDetails = topFlopDetails;
        this.dataName = dataName;
        this.imageCompetition = imageCompetition;
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
                systemData(null, creatorUsername),
                password,
                confirmedPassword,
                details,
                topFlopDetails,
                dataName,
                null,
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
