package com.lhc.datamodel.repository;

import com.lhc.datamodel.entities.VoteRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<VoteRecord, Long> {

    @Query("Select vr from Votes vr where vr.reference = :ref")
    VoteRecord findByReference(@Param("ref") String ref);

    @Query("Select vr from Votes vr where vr.ballotRecord in " +
            "(Select br from Ballots br where br.matchRecord.reference = :match_ref)")
    List<VoteRecord> findAllByMatchReference(@Param("match_ref") String match_ref);

    @Query("Select vr from Votes vr where vr.ballotRecord.reference = :ballot_ref")
    List<VoteRecord> findAllByBallotReference(@Param("ballot_ref") String ballot_ref);


}
