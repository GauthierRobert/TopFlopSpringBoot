package com.lhc.datamodel.entities;

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

    public static final Competition EMPTY_COMPETITION = new Competition(){
        @Override
        public Boolean isExist() {
            return false;
        }
    };

    private Competition() {

    }

    public static Competition competition(){
        return new Competition();
    }

    public static Competition competition(Long id, String name, int season, String division, String password, String confirmedPassword, String creatorUsername, List<Rule> rules) {
        return new Competition(
                id,
                name,
                name,
                createSeason(season),
                division,
                password,
                confirmedPassword,
                creatorUsername,
                new ArrayList<>(),
                new ArrayList<>(),
                rules
        );
    }

    private static String createSeason(int season) {

        return season + " - " + (season + 1);

    }

    public Competition(Long id, String reference, String name, String season, String division, String password, String confirmedPassword, String creatorUsername, List<Match> matches, List<User> allowedUsers, List<Rule> rules) {
        this.id = id;
        this.reference = reference;
        this.name = name;
        this.season = season;
        this.division = division;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
        this.creatorUsername = creatorUsername;
        this.matches = matches;
        this.allowedUsers = allowedUsers;
        this.rules = rules;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String reference;

    private String name;

    private String season;

    private String division;

    private String password;
    @Transient
    private String confirmedPassword;

    private String creatorUsername;

    @OneToMany(mappedBy="competition", fetch= FetchType.LAZY, cascade= CascadeType.ALL )
    private List<Match> matches;

    @ManyToMany
    @JoinTable(name = "users_competitions",
            joinColumns = @JoinColumn(
                    name = "competition_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"))
    private List<User> allowedUsers;

    @OneToMany(mappedBy="competition", fetch= FetchType.EAGER, cascade= CascadeType.ALL )
    private List<Rule> rules;

    public Boolean isExist(){
        return true;
    }

    public String hasError(){
        return null;
    }

}
