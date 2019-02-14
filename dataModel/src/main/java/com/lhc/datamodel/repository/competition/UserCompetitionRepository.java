package com.lhc.datamodel.repository.competition;

import com.lhc.datamodel.entities.competition.Competition;
import com.lhc.datamodel.entities.competition.manyToMany.UserCompetition;
import com.lhc.datamodel.entities.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCompetitionRepository extends JpaRepository<UserCompetition, Long> {

    @Query("DELETE FROM Users_Competitions uc " +
            "JOIN uc.user u" +
            "JOIN uc.competition c" +
            "WHERE :username = u.username" +
            "AND :competition_ref = c.reference")
    Long delete(@Param("username") String username, @Param("competition_ref") String competition_ref );


}
