package com.lhc.datamodel.repository;

import com.lhc.datamodel.entities.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("Select v from Votes v where v.reference = :ref")
    Vote findByReference(@Param("ref") String ref);

    @Query("Select v from Votes v where v.ballot in " +
            "(Select b from Ballots b where b.match.reference = :match_ref)")
    List<Vote> findAllByMatchReference(@Param("match_ref") String match_ref);

    @Query("Select v from Votes v where v.ballot.reference = :ballot_ref")
    List<Vote> findAllByBallotReference(@Param("ballot_ref") String ballot_ref);


}
