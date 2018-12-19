package com.lhc.datamodel.repository;

import com.lhc.datamodel.entities.MatchRecord;
import com.lhc.datamodel.entities.VoteRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<MatchRecord, Long> {

    @Query("Select mr from Matches mr where mr.reference = :ref")
    MatchRecord findByReference(@Param("ref") String ref);


    @Query("Select mr from Matches mr where mr.competitionRecord.reference = :competition_ref")
    List<MatchRecorda> findAllByCompetitionReference(@Param("competition_ref") String competition_ref);


}
