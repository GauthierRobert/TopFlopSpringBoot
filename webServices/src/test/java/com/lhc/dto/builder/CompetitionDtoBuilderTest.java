package com.lhc.dto.builder;

import com.google.gson.Gson;
import com.lhc.datamodel.enumeration.Sport;
import com.lhc.dto.CompetitionDto;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static com.lhc.dto.builder.CompetitionDtoBuilder.aCompetitionDto;
import static com.lhc.dto.builder.CompetitionDtoBuilder.aCompetitionDtoCreatedBy;

public class CompetitionDtoBuilderTest {

    public static CompetitionDto competitionDtoTest = aCompetitionDtoCreatedBy("AMO")
            .withPassword("AAAA")
            .withConfirmedPassword("AAAA")
            .finallySport(Sport.FOOTBALL)
            .withDivision("D1")
            .withSeason("2018-2019")
            .withName("Linkebeek")
            .finallyStatisticsName("Nombre de but",null, null, null, null)
            .withComments(true, false)
            .withTopFlopName("Carré d'as", "Floge")
            .finallyRules(4,2)
            .withTopRuleDtos(4,3,2,1)
            .withFlopRuleDtos(4,2)
            .build();

    @Test
    public void BuilderDtoTest() {


        Gson gson = new Gson();
        String json = gson.toJson(competitionDtoTest);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(json);

    }
}