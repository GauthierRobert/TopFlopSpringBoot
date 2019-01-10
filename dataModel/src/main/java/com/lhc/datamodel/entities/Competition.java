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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String reference;

    private String name;

    private int season;

    private String division;

    private String password;
    @Transient
    private String confirmedPassword;

    private String creatorUsername;

    @OneToMany(mappedBy="competition", fetch= FetchType.LAZY, cascade= CascadeType.ALL )
    private List<Match> Matches = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "users_competitions",
            joinColumns = @JoinColumn(
                    name = "competition_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"))
    private List<User> allowedUsers = new ArrayList<>();

    @OneToMany(mappedBy="competition", fetch= FetchType.EAGER, cascade= CascadeType.ALL )
    private List<Rule> rules = new ArrayList<>();

    public Boolean isExist(){
        return true;
    }

    public String hasError(){
        return null;
    }

}
