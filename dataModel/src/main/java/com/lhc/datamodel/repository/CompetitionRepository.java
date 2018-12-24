package com.lhc.datamodel.repository;

import com.lhc.datamodel.entities.CompetitionRecord;
import com.lhc.datamodel.entities.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {

    @Query("Select c from Competitions c where c.reference = :ref")
    Competition findByReference(@Param("ref") String ref);

    @Query("Select c from Competitions c where :user in c.allowedUsers")
    List<Competition> findAllByUser(@Param("user") User user);

    @Query("Select c from Competitions c where :username in c.allowedUsers.username")
    List<Competition> findAllByUsername(@Param("username") String username);

}
