package com.lhc.datamodel.entities;

import com.lhc.datamodel.entities.rules.RuleRecord;
import com.lhc.datamodel.entities.rules.Rules;
import com.lhc.datamodel.entities.security.User;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "Competitions")
@Table(name = "Competitions")
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String password;

    private String reference;

    @OneToMany(mappedBy="competition", fetch= FetchType.LAZY, cascade= CascadeType.ALL )
    private List<com.lhc.datamodel.entities.Match> Matches = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "users_competitions",
            joinColumns = @JoinColumn(
                    name = "competition_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"))
    private List<User> allowedUsers = new ArrayList<>();

    @OneToMany(mappedBy="competition", fetch= FetchType.EAGER, cascade= CascadeType.ALL )
    private List<com.lhc.datamodel.entities.rules.Rule> rules = new ArrayList<>();

}