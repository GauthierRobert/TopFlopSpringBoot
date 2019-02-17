package com.lhc.webservices.restServices;

import com.lhc.dto.CompetitionDto;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
public interface CompetitionEndPoint {

    @RequestMapping(
            value = "/competition/save",
            method = RequestMethod.POST)
    CompetitionDto postCompetition(@RequestBody CompetitionDto competitionDto) throws NoSuchAlgorithmException;


    @RequestMapping(
            value = "/competition/addUser",
            method = RequestMethod.POST)
    CompetitionDto addUserToCompetition(@RequestParam(value = "competition_ref") String competition_ref,
                                        @RequestParam(value = "username") String username,
                                        @RequestParam(value = "password") String password,
                                        @RequestParam(value = "isPlayer") boolean isPlayer) throws NoSuchAlgorithmException;

    @RequestMapping(
            value = "/competition/removeUser",
            method = RequestMethod.POST)
    void removeUserFromCompetition(@RequestParam(value = "username") String username,
                                             @RequestParam(value = "competition_ref") String competition_ref) throws NoSuchAlgorithmException;

    @RequestMapping(
            value = "/competition/delete",
            method = RequestMethod.POST)
    void deleteCompetition(@RequestParam(value = "competition_ref") String competition_ref) throws NoSuchAlgorithmException;

    @RequestMapping(
            value = "/competition/getList",
            method = RequestMethod.GET)
    List<CompetitionDto> getCompetitionLinkToUser(@RequestParam(value = "username") String username);

    @RequestMapping(
            value = "/user/getList",
            method = RequestMethod.GET)
    List<String> getUsersLinkToCompetition(@RequestParam(value = "competition_ref") String competition_ref);
}
