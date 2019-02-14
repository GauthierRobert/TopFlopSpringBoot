package com.lhc.datamodel.repository.competition;

import com.lhc.datamodel.entities.SystemData;
import com.lhc.datamodel.entities.competition.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {

    @Query("SELECT c FROM Competitions c " +
            "WHERE c.system_data.reference = :ref")
    Competition findByReference(@Param("ref") String ref);

    @Query("SELECT c FROM Competitions c " +
            "WHERE c.details.name = :name")
    Competition findByName(@Param("name") String name);

    @Query("SELECT c FROM Competitions c " +
            "JOIN c.userCompetitions uc " +
            "JOIN uc.user u " +
            "WHERE :username = u.username")
    List<Competition> findAllByUsername(@Param("username") String username);

    @Query("SELECT c.system_data FROM Competitions c " +
            "JOIN c.userCompetitions uc " +
            "JOIN uc.user u " +
            "WHERE :username = u.username")
    List<SystemData> findAllSystemDataByUsername(@Param("username") String username);

    @Query("SELECT u.username FROM Competitions c " +
            "JOIN c.userCompetitions uc " +
            "JOIN uc.user u " +
            "WHERE c.system_data.reference = :ref AND uc.isPlayer = TRUE")
    List<String> findPlayerByCompetition(@Param("ref") String ref);


    Long deleteByReference(String reference);

}
