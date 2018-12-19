package com.lhc.datamodel.repository;

import com.lhc.datamodel.entities.CompetitionRecord;
import com.lhc.datamodel.entities.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetitionRepository extends JpaRepository<CompetitionRecord, Long> {

    @Query("Select cr from Competitions cr where cr.reference = :ref")
    CompetitionRecord findByReference(@Param("ref") String ref);

    @Query("Select cr from Competitions cr where :user in cr.allowedUsers")
    List<CompetitionRecord> findAllByUser(@Param("user") User user);

    @Query("Select cr from Competitions cr where :username in cr.allowedUsers.username")
    List<CompetitionRecord> findAllByUsername(@Param("username") String username);

}
