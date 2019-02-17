package com.lhc.datamodel.repository.competition;

import com.lhc.datamodel.entities.competition.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("Select m from Matches m where m.systemData.reference = :reference")
    Match findByReference(@Param("reference") String reference);

    @Query("DELETE FROM Matches m " +
            "WHERE m.systemData.reference = :reference")
    void deleteByReference(@Param("reference") String reference);

    @Query("Select m from Matches m where m.competition.systemData.reference = :competition_ref")
    List<Match> findAllByCompetitionReference(@Param("competition_ref") String competition_ref);


}
