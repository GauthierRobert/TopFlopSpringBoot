package com.lhc.mapper.competition;

import com.lhc.datamodel.entities.competition.Competition;
import com.lhc.datamodel.enumeration.Sport;
import com.lhc.dto.CompetitionDto;
import com.lhc.mapper.mapperHandler.CompetitionMapperHandler;
import org.junit.Test;

import static com.lhc.datamodel.builder.CompetitionBuilder.aCompetitionCreatedBy;
import static org.assertj.core.api.Assertions.assertThat;

public class CompetitionMapperHandlerTest {

    private static Competition competitionTest = aCompetitionCreatedBy("Me")
            .withPassword("AAAA")
            .withConfirmedPassword("AAAA")
            .finallySport(Sport.HOCKEY)
            .withName("Linkebeek")
            .withDivision("D1")
            .withSeason("2018")
            .finallyStatisicsName("Nombre de but",null, null, null, null)
            .withComments(true, true)
            .finallyRules(4,2)
            .withTopRules(4,3,2,1)
            .withFlopRules(4,2)
            .build();

    @Test
    public void createDTOFromEntity() {

        Competition entity = competitionTest;
        entity.setReference("Reference");
        CompetitionMapperHandler competitionMapperHandler = new CompetitionMapperHandler();
        CompetitionDto actual = competitionMapperHandler.createDTOFromEntity(entity);
        assertThat(actual.getCreatorUsername()).isEqualTo(entity.getCreatorUsername());

    }
}