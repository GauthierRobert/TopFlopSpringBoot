package com.lhc.webservices.restServices;

import com.lhc.dto.ImageCompetitionDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.security.NoSuchAlgorithmException;

@Produces(MediaType.APPLICATION_JSON)
public interface ImageUploadEndPoint {

    @RequestMapping(
            value = "/competition/image",
            method = RequestMethod.POST)
    ImageCompetitionDto updateImageCompetition(@RequestBody ImageCompetitionDto ImageCompetitionDto) throws NoSuchAlgorithmException;
}
