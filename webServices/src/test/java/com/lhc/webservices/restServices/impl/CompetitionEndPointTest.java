package com.lhc.webservices.restServices.impl;

import com.lhc.business.BusinessConfig;
import com.lhc.business.service.competition.CompetitionService;
import com.lhc.datamodel.entities.competition.Competition;
import com.lhc.datamodel.entities.security.User;
import com.lhc.dto.CompetitionDto;
import com.lhc.mapper.mapperHandler.CompetitionMapperHandler;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.NoSuchAlgorithmException;

import static com.lhc.dto.builder.CompetitionDtoBuilderTest.competitionDtoTest;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes =  BusinessConfig.class)
public class CompetitionEndPointTest {

    @Autowired
    private CompetitionService competitionService;

    private static final String USERNAME = "AAAA";
    private static final User USER = new User(USERNAME, "AAAA");
    private static final String URL = "http://ec2-52-47-206-114.eu-west-3.compute.amazonaws.com:8080/RestServices/competition/save";

    @Test
    public void TestInsidePostRequest() {
        CompetitionDto competitionDto = competitionDtoTest;
        User currentUser = USER;

        CompetitionMapperHandler competitionMapperHandler = new CompetitionMapperHandler();
        Competition competition = null;
        if (competitionDto.getConfirmedPassword().equals(competitionDto.getPassword())) {
            competition = competitionMapperHandler.createEntityFromDTO(competitionDto);
            try {
                competition = competitionService.saveOrUpdate(competition);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

        }
        System.out.println(competition);
    }
}