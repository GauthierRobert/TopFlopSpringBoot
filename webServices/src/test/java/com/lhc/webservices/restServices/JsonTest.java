package com.lhc.webservices.restServices;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lhc.dto.CompetitionDto;
import org.junit.Test;

import java.io.IOException;

public class JsonTest {

    private static String mRequest = "{\n" +
            "  \"confirmedPassword\": \"a\",\n" +
            "  \"creatorUsername\": \"Gauthier\",\n" +
            "  \"dataName\": {\n" +
            "    \"dataName_1\": \"Goals\"\n" +
            "  },\n" +
            "  \"details\": {\n" +
            "    \"division\": \"g\",\n" +
            "    \"name\": \"ghbg\",\n" +
            "    \"season\": \"g\",\n" +
            "    \"sport\": null" +
            "  },\n" +
            "  \"matchDtos\": [],\n" +
            "  \"password\": \"a\",\n" +
            "  \"ruleDtos\": [\n" +
            "    {\n" +
            "      \"indication\": 0,\n" +
            "      \"label\": \"NUMBER_VOTE_TOP\",\n" +
            "      \"points\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"indication\": 0,\n" +
            "      \"label\": \"NUMBER_VOTE_FLOP\",\n" +
            "      \"points\": 0\n" +
            "    }\n" +
            "  ],\n" +
            "  \"topFlopDetails\": {\n" +
            "    \"flopName\": \"ghg\",\n" +
            "    \"topName\": \"xgg\",\n" +
            "    \"withCommentFlop\": false,\n" +
            "    \"withCommentTop\": false\n" +
            "  }\n" +
            "}";

    @Test
    public void Json() {

        ObjectMapper mapper = new ObjectMapper();
        CompetitionDto competitionDto;


        try {
            competitionDto = mapper.readValue(mRequest, CompetitionDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Ok");
    }


}