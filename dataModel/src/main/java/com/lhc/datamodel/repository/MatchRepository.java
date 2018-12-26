package com.lhc.datamodel.repository;

import com.lhc.datamodel.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("Select m from Matches m where m.reference = :ref")
    Match findByReference(@Param("ref") String ref);


    @Query("Select m from Matches m where m.competition.reference = :competition_ref")
    List<Match> findAllByCompetitionReference(@Param("competition_ref") String competition_ref);


}
