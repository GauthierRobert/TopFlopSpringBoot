package com.lhc.datamodel.entities.security;


import com.lhc.datamodel.entities.competition.Ballot;
import com.lhc.datamodel.entities.competition.Competition;
import com.lhc.datamodel.entities.competition.manyToMany.UserCompetition;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "User")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String username;

    private String password;
    
    @Transient
    private String passwordConfirm;

    @OneToMany(mappedBy = "user")
    private List<UserCompetition> userCompetitions;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy="user", fetch= FetchType.LAZY, cascade= CascadeType.ALL )
	private List<Ballot> ballots;

    public Long getId() {
        return id;
    }

	public String getPassword() {
        return password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getUsername() {
        return username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }
}