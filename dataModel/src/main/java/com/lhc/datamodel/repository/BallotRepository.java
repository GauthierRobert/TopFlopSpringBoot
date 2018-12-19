package com.lhc.datamodel.repository;

import com.lhc.datamodel.entities.BallotRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BallotRepository extends JpaRepository<BallotRecord, Long> {

    @Query("Select br from Ballots br where br.matchRecord.reference = :match_ref")
    List<BallotRecord> findAllByMatchReference(@Param("match_ref") String match_ref);

    @Query("Select br from Ballots br where br.reference = :ref")
    BallotRecord findByReference(@Param("ref") String ref);

}
