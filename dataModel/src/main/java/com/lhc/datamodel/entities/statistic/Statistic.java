package com.lhc.datamodel.entities.statistic;


import com.lhc.datamodel.entities.competition.Match;
import com.lhc.datamodel.entities.security.User;
import com.lhc.datamodel.entities.statistic.embedded.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "Statistics")
@Entity(name = "Statistics")
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String reference;

    @ManyToOne
    private Match match;

    @ManyToOne
    private User player;

    @Embedded
    private Data data;

    private Statistic() {
    }

    private Statistic(Long id, String reference, Match match, User player, Data data) {
        this.id = id;
        this.reference = reference;
        this.match = match;
        this.player = player;
        this.data = data;
    }

    public static Statistic statistic(String reference, Match match, User player, Data data){
        return new Statistic(null, reference, match, player, data);
    }

    public static Statistic statistic(){
        return new Statistic();
    }


}
