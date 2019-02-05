package com.lhc.mapper.competition;

import com.lhc.datamodel.entities.competition.Competition;
import com.lhc.dto.CompetitionDto;
import org.junit.Test;

import static com.lhc.datamodel.builder.CompetitionBuilder.aCompetition;
import static org.assertj.core.api.Assertions.assertThat;

public class CompetitionMapperHandlerTest {

    private static Competition competitionTest = aCompetition()
            .withName("Linkebeek")
            .withDivision("D1")
            .withSeason(2018)
            .withCreatorUsername("Me")
            .withPassword("AAAA")
            .withConfirmedPassword("AAAA")
            .withRules(4,2)
            .withTopRules(4,3,2,1)
            .withFlopRules(4,2)
            .build();

    @Test
    public void createDTOFromEntity() {

        Competition entity = competitionTest;
        CompetitionMapperHandler competitionMapperHandler = new CompetitionMapperHandler();
        CompetitionDto actual = competitionMapperHandler.createDTOFromEntity(entity);

        assertThat(actual.getName()).isEqualTo(entity.getName());

    }
}