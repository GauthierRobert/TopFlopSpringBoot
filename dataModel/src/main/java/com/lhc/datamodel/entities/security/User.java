package com.lhc.datamodel.entities.security;


import com.lhc.datamodel.entities.BallotRecord;
import com.lhc.datamodel.entities.CompetitionRecord;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
	
	@Id
    private Long id;
    private String username;
    private String password;
    
    @Transient
    private String passwordConfirm;

    @ManyToMany(mappedBy = "allowedUsers")
    private List<Competition> competitions;

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

    public List<Ballot> getBallots() {
        return ballots;
    }
    
    public void setBallots(List<Ballot> ballots) {
        this.ballots = ballots;
    }

    public List<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
    }
}