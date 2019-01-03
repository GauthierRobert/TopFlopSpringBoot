package com.lhc.datamodel.entities;


import com.lhc.datamodel.entities.security.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Table(name = "Ballots")
@Entity(name = "Ballots")
public class Ballot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String reference;
    
    private String match_ref;
    
    private String competition_ref;

    @OneToMany(mappedBy="ballot", fetch= FetchType.LAZY, cascade= CascadeType.ALL )
    private List<Vote> votes;

    @ManyToOne
    private Match match;

    @ManyToOne
    private User user;



}
