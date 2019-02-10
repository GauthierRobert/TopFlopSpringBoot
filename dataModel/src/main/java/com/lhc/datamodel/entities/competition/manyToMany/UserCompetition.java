package com.lhc.datamodel.entities.competition.manyToMany;

import com.lhc.datamodel.entities.competition.Competition;
import com.lhc.datamodel.entities.security.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@Entity(name = "users_competitions")
@Table(name = "users_competitions")
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

    private UserCompetition() {
    }

    private UserCompetition(User user, Competition competition, boolean isPlayer) {
        this.user = user;
        this.competition = competition;
        this.isPlayer = isPlayer;
    }

    public static UserCompetition userCompetition(){
        return new UserCompetition();
    }

    public static UserCompetition player(User user, Competition competition) {
        return new UserCompetition(user, competition, true);
    }

    public static UserCompetition spectator(User user, Competition competition) {
        return new UserCompetition(user, competition, false);
    }
}
