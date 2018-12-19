package com.lhc.datamodel.repository;

import com.lhc.datamodel.entities.MatchRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<MatchRecord, Long> {

    @Query("Select mr from Matches mr where mr.reference = :ref")
    MatchRecord findByReference(@Param("ref") String ref);


}
