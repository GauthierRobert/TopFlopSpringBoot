package com.lhc.datamodel.entities.competition;

import com.lhc.datamodel.entities.image.ImageCompetition;
import com.lhc.datamodel.entities.rules.Rule;
import com.lhc.datamodel.entities.security.User;
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

    @Column(columnDefinition = "LONGVARCHAR")
    private Boolean withCommentTop;

    @Column(columnDefinition = "LONGVARCHAR")
    private Boolean withCommentFlop;

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

    @ManyToMany
    @JoinTable(name = "users_competitions",
            joinColumns = @JoinColumn(
                    name = "competition_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"))
    private List<User> allowedUsers;

    @OneToMany(mappedBy="competition", fetch= FetchType.EAGER, cascade= CascadeType.ALL )
    private List<Rule> rules;



    private Competition() {
        this.matches = new ArrayList<>();
        this.allowedUsers = new ArrayList<>();
        this.rules = new ArrayList<>();
        this.trainings = new ArrayList<>();
    }

    public static Competition competition(){
        return new Competition();
    }

    public static Competition competition(Long id, String name, String season, String division, String password, String confirmedPassword, String creatorUsername, boolean withCommentTop, boolean withCommentFlop, List<Rule> rules) {
        Competition competition = new Competition(
                id,
                name,
                name,
                season,
                division,
                password,
                confirmedPassword,
                creatorUsername,
                withCommentTop,
                withCommentFlop,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );
        competition.setRules(rules);
        return competition;
    }


    private Competition(Long id, String reference, String name, String season, String division, String password, String confirmedPassword, String creatorUsername, Boolean withCommentTop, Boolean withCommentFlop, List<Match> matches, List<User> allowedUsers, List<Rule> rules, List<Training> trainings) {
        this.id = id;
        this.reference = reference;
        this.name = name;
        this.season = season;
        this.division = division;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
        this.creatorUsername = creatorUsername;
        this.withCommentTop = withCommentTop;
        this.withCommentFlop = withCommentFlop;
        this.matches = matches;
        this.trainings = trainings;
        this.allowedUsers = allowedUsers;
        this.rules = rules;
    }

    public void setRules(List<Rule> rules) {
        for(Rule rule : rules){
            rule.setCompetition(this);
        }
        this.rules = rules;
    }




}
