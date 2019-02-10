package com.lhc.webservices.restServices.impl;

import com.lhc.business.service.competition.CompetitionService;
import com.lhc.business.service.image.ImageUploadService;
import com.lhc.datamodel.entities.image.ImageCompetition;
import com.lhc.dto.ImageCompetitionDto;
import com.lhc.mapper.mapperHandler.ImageCompetitionMapperHandler;
import com.lhc.webservices.restServices.ImageUploadEndPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
@RestController
public class ImageUploadEndPointImpl implements ImageUploadEndPoint {


    @Autowired
    private ImageUploadService imageUploadService;
    @Autowired
    private CompetitionService competitionService;


    @Override
    public ImageCompetitionDto updateImageCompetition(ImageCompetitionDto ImageCompetitionDto) throws NoSuchAlgorithmException {

        ImageCompetitionMapperHandler imageCompetitionMapperHandler = new ImageCompetitionMapperHandler();
        ImageCompetition imageCompetition = imageCompetitionMapperHandler.createEntityFromDTO(ImageCompetitionDto);

        imageCompetition.setCompetition(competitionService.findByReference(ImageCompetitionDto.getCompetition_ref()));

        return imageCompetitionMapperHandler.createDTOFromEntity(imageUploadService.saveOrUpdate(imageCompetition));

    }
}
