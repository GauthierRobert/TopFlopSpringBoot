package com.lhc.datamodel.repository.competition;

import com.lhc.datamodel.entities.competition.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("SELECT v FROM Votes v WHERE v.reference = :ref")
    Vote findByReference(@Param("ref") String ref);

    @Query("SELECT v FROM Votes v WHERE v.ballot.reference = :ballot_ref")
    List<Vote> findAllByBallotReference(@Param("ballot_ref") String ballot_ref);

    @Query("SELECT v FROM Votes v WHERE v.ballot in " +
           "(SELECT b FROM Ballots b WHERE b.match.systemData.reference = :match_ref)")
    List<Vote> findAllByMatchReference(@Param("match_ref") String match_ref);

    @Query("SELECT v FROM Votes v " +
           "WHERE v.ballot in (SELECT b FROM Ballots b WHERE b.match.systemData.reference = :match_ref) " +
           "AND v.ballot.counted = TRUE ")
    List<Vote> findAllCountedByMatchReference(String match_ref);
}
