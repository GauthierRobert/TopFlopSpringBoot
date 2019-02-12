package com.lhc.datamodel.builder;

import com.lhc.datamodel.entities.competition.Competition;
import com.lhc.datamodel.enumeration.Sport;
import org.junit.Test;

import static com.lhc.datamodel.builder.CompetitionBuilder.aCompetitionCreatedBy;

public class CompetitionBuilderTest {


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
    public void builderTest() {


        System.out.println(competitionTest);
    }
}