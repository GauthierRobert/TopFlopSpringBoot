package com.lhc.webservices.restServices.impl;

import com.lhc.business.service.competition.CompetitionService;
import com.lhc.business.service.security.UserService;
import com.lhc.datamodel.entities.competition.Competition;
import com.lhc.datamodel.entities.security.User;
import com.lhc.dto.CompetitionDto;
import com.lhc.mapper.mapperHandler.CompetitionMapperHandler;
import com.lhc.webservices.restServices.CompetitionEndPoint;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompetitionEndPointImpl implements CompetitionEndPoint {


    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private UserService userService;


    @Override
    public CompetitionDto saveCompetition(@RequestBody CompetitionDto competitionDto) throws NoSuchAlgorithmException {

        CompetitionMapperHandler competitionMapperHandler = new CompetitionMapperHandler();

        if (competitionDto.getConfirmedPassword().equals(competitionDto.getPassword())) {
            Competition competition = competitionMapperHandler.createEntityFromDTO(competitionDto);
            competition = competitionService.saveOrUpdate(competition);
            competitionDto = competitionMapperHandler.createDTOFromEntity(competition);
            return competitionDto;
        } else {
            return null;
        }
    }

    @Override
    public CompetitionDto updateCompetition(CompetitionDto competitionDto) throws NoSuchAlgorithmException {

        CompetitionMapperHandler competitionMapperHandler = new CompetitionMapperHandler();
        Competition competition = Competition.competition();

        if (competitionDto.getSystemDataDto().getReference() != null) {
            competition = competitionService.findByReference(competitionDto.getSystemDataDto().getReference());
        }

        competitionMapperHandler.mapToEntity(competitionDto, competition);
        competition = competitionService.saveOrUpdate(competition);

        return competitionMapperHandler.createDTOFromEntity(competition);
    }


    @Override
    public CompetitionDto addUserToCompetition(@RequestParam(value = "competition_ref") String competition_ref,
                                               @RequestParam(value = "username") String username,
                                               @RequestParam(value = "password") String password,
                                               @RequestParam(value = "isPlayer") boolean isPlayer) throws NoSuchAlgorithmException {

        User currentUser = userService.findByUsername(username);
        Competition competition;
        competition = competitionService.addUser(currentUser, competition_ref, password, isPlayer);
        CompetitionMapperHandler competitionMapperHandler = new CompetitionMapperHandler();
        CompetitionDto competitionDto = competitionMapperHandler.createDTOFromEntity(competition);
        return competitionDto;

    }

    @Override
    public void removeUserFromCompetition(String username, String competition_ref) {
        competitionService.deleteUserFromCompetition(username, competition_ref);
    }

    @Override
    public void deleteCompetition(String competition_ref) {
        competitionService.deleteCompetition(competition_ref);
    }


    @Override
    public List<CompetitionDto> getCompetitionLinkToUser(@RequestParam(value = "username") String username) {

        List<Competition> competitions = competitionService.findAllByUsername(username);
        CompetitionMapperHandler competitionMapperHandler = new CompetitionMapperHandler();

        return competitionMapperHandler.mapToListDtos(competitions);

    }

    @Override
    public List<String> getUsersLinkToCompetition(@RequestParam(value = "competition_ref") String competition_ref) {

        return competitionService.findUsersByCompetition(competition_ref);
    }
}
