package com.lhc.datamodel.repository;

import com.lhc.datamodel.entities.Competition;
import com.lhc.datamodel.entities.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {

    @Query("SELECT c FROM Competitions c WHERE c.reference = :ref")
    Competition findByReference(@Param("ref") String ref);

    @Query("SELECT c FROM Competitions c WHERE :user = c.allowedUsers")
    List<Competition> findAllByUser(@Param("user") User user);

    @Query("SELECT c FROM Competitions c JOIN c.allowedUsers u where :username = u.username")
    List<Competition> findAllByUsername(@Param("username") String username);

}
