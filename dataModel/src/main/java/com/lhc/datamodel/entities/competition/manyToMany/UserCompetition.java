package com.lhc.datamodel.entities.competition.manyToMany;

import com.lhc.datamodel.entities.competition.Competition;
import com.lhc.datamodel.entities.security.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@Entity(name = "user_competitions")
@Table(name = "user_competitions")
@IdClass(UserCompetitionId.class)
public class UserCompetition implements Serializable {



    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "competition_id", referencedColumnName = "id")
    private Competition competition;

    @Column(name = "is_player")
    private boolean isPlayer;

    private UserCompetition(User user, boolean isPlayer) {
        this.user = user;
        this.isPlayer = isPlayer;
    }

    public static UserCompetition player(User user){
        return new UserCompetition(user, true);
    }

    public static UserCompetition spectator(User user){
        return new UserCompetition(user, false);
    }
}
