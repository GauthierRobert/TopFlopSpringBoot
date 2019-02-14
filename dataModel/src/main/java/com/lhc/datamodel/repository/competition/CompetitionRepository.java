package com.lhc.datamodel.repository.competition;

import com.lhc.datamodel.entities.competition.Competition;
import com.lhc.datamodel.entities.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {

    @Query("SELECT c FROM Competitions c " +
            "WHERE c.reference = :ref")
    Competition findByReference(@Param("ref") String ref);

    @Query("SELECT c FROM Competitions c " +
            "WHERE c.details.name = :name")
    Competition findByName(@Param("name") String name);

    @Query("SELECT c FROM Competitions c " +
            "WHERE :user = c.userCompetitions")
    List<Competition> findAllByUser(@Param("user") User user);

    @Query("SELECT c FROM Competitions c " +
            "JOIN c.userCompetitions uc " +
            "JOIN uc.user u " +
            "WHERE :username = u.username")
    List<Competition> findAllByUsername(@Param("username") String username);

    @Query("SELECT u.username FROM Competitions c " +
            "JOIN c.userCompetitions uc " +
            "JOIN uc.user u " +
            "WHERE c.reference = :ref AND uc.isPlayer = TRUE")
    List<String> findPlayerByCompetition(@Param("ref") String ref);


    Long deleteByReference(String reference);

}
