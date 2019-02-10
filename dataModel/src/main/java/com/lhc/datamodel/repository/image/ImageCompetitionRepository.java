package com.lhc.datamodel.repository.image;

import com.lhc.datamodel.entities.image.ImageCompetition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageCompetitionRepository extends JpaRepository<ImageCompetition, Long> {

    @Query("Select i_c from Images_Competition i_c where i_c.competition_ref = :competition_ref")
    ImageCompetition findByCompetitionReference(@Param("competition_ref") String competition_ref);


}
