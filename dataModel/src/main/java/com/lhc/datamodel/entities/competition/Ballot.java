package com.lhc.datamodel.entities.competition;


import com.lhc.datamodel.entities.security.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Table(name = "Ballots")
@Entity(name = "Ballots")
public class Ballot implements Serializable {


    private Ballot() {

    }

    public static Ballot ballot(){
        return new Ballot();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String reference;

    private String match_ref;

    private String competition_ref;

    private String comment;

    @OneToMany(mappedBy="ballot", fetch= FetchType.EAGER, cascade= CascadeType.ALL )
    private List<Vote> votes;

    @ManyToOne
    private Match match;

    @ManyToOne
    private User user;


}
