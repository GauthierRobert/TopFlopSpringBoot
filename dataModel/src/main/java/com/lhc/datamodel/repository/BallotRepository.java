package com.lhc.datamodel.repository;

import com.lhc.datamodel.entities.BallotRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BallotRepository extends JpaRepository<Ballot, Long> {

    @Query("Select b from Ballots b where b.match.reference = :match_ref")
    List<Ballot> findAllByMatchReference(@Param("match_ref") String match_ref);

    @Query("Select b from Ballots b where b.reference = :ref")
    Ballot findByReference(@Param("ref") String ref);

}
