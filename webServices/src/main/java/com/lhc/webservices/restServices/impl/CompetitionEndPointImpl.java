package com.lhc.webservices.restServices.impl;

import com.lhc.business.service.CompetitionService;
import com.lhc.business.service.security.UserService;
import com.lhc.datamodel.entities.Competition;
import com.lhc.datamodel.entities.security.User;
import com.lhc.dto.CompetitionDto;
import com.lhc.mapper.competition.CompetitionMapperHandler;
import com.lhc.webservices.restServices.CompetitionEndPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public class CompetitionEndPointImpl implements CompetitionEndPoint {


    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private UserService userService;


    @Override
    public CompetitionDto postCompetition(@RequestBody CompetitionDto competitionDto) throws NoSuchAlgorithmException {

        User currentUser = userService.findByUsername(competitionDto.getCreatorUsername());

        CompetitionMapperHandler competitionMapperHandler = new CompetitionMapperHandler();

        if (competitionDto.getConfirmedPassword().equals(competitionDto.getPassword())) {
            Competition competition = competitionMapperHandler.createEntityFromDTO(competitionDto);
            competitionService.createCompetition(competition, currentUser);
            return competitionDto;
        } else {
            return null;
        }
    }



    @Override
    public CompetitionDto addUserToCompetition(@RequestParam(value = "competition_ref") String competition_ref,
                                               @RequestParam(value = "username") String username,
                                               @RequestParam(value = "password") String password) throws NoSuchAlgorithmException {

        User currentUser = userService.findByUsername(username);
        Competition competition = competitionService.addUserToCompetition(currentUser, competition_ref, password);

        CompetitionMapperHandler competitionMapperHandler = new CompetitionMapperHandler();
        CompetitionDto competitionDto = competitionMapperHandler.createDTOFromEntity(competition);
        return competitionDto;

    }


    @Override
    public List<CompetitionDto> getCompetitionLinkToUser(@RequestParam(value = "username") String username){

        List<Competition> competitions = competitionService.findAllByUsername(username);
        CompetitionMapperHandler competitionMapperHandler = new CompetitionMapperHandler();
        
        return competitionMapperHandler.mapToListDtos(competitions);

    }

    @Override
    public List<String> getUsersLinkToCompetition(@RequestParam(value = "competition_ref") String competition_ref){

        return competitionService.findUsersbyCompetition(competition_ref);
    }
}
