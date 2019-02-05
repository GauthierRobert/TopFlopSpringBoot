package com.lhc.datamodel.repository.competition;

import com.lhc.datamodel.entities.competition.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

    @Query("Select m from Trainings m where m.reference = :ref")
    Training findByReference(@Param("ref") String ref);

    @Query("Select m from Trainings m where m.competition.reference = :competition_ref")
    List<Training> findAllByCompetitionReference(@Param("competition_ref") String competition_ref);


}
