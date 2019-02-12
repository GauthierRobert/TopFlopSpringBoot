package com.lhc.datamodel.entities.competition;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@Entity(name = "Votes")
@Table(name = "Votes")
public class Vote  implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String reference;

    @Column(unique=true)
    private String competition_ref;

    private String name;

    private Integer points;

    // 1 for first top vote
    // 2 for second top vote
    // ...
    // -1 for first flop vote
    // -2 for second flop vote
    private Integer indication;

    @ManyToOne
    private Ballot ballot;

    private Vote(){

    }

    public static Vote vote(){
        return new Vote();
    }
}
