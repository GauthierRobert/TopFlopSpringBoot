package com.lhc.dto.builder;

import com.google.gson.Gson;
import com.lhc.dto.CompetitionDto;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static com.lhc.dto.builder.CompetitionDtoBuilder.aCompetitionDto;

public class CompetitionDtoBuilderTest {

    public static CompetitionDto competitionDtoTest = aCompetitionDto()
            .withName("Linkebeek")
            .withDivision("D1")
            .withSeason("2018-2019")
            .withCreatorUsername("AAAA")
            .withComments(true, false)
            .withPassword("AAAA")
            .withConfirmedPassword("AAAA")
            .withRuleDtos(4,2)
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