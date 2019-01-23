package com.lhc.datamodel.builder;

import com.lhc.datamodel.entities.Competition;
import org.junit.Test;

import static com.lhc.datamodel.builder.CompetitionBuilder.aCompetition;
import static org.junit.Assert.*;

public class CompetitionBuilderTest {


    private static Competition competitionTest = aCompetition()
            .withName("Linkebeek")
            .withDivision("D1")
            .withSeason(2018)
            .withCreatorUsername("Me")
            .withPassword("AAAA")
            .withConfirmedPassword("AAAA")
            .withComments()
            .withRules(4,2)
            .withTopRules(4,3,2,1)
            .withFlopRules(4,2)
            .build();

    @Test
    public void builderTest() {


        System.out.println(competitionTest);
    }
}