package com.lhc.webservices.restServices.impl;

import com.lhc.business.service.image.ImageUploadService;
import com.lhc.datamodel.entities.image.ImageCompetition;
import com.lhc.dto.CompetitionDto;
import com.lhc.dto.ImageCompetitionDto;
import com.lhc.mapper.mapperHandler.ImageCompetitionMapperHandler;
import com.lhc.webservices.restServices.ImageUploadEndPoint;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.NoSuchAlgorithmException;

public class ImageUploadEndPointImpl implements ImageUploadEndPoint {


    @Autowired
    private ImageUploadService imageUploadService;


    @Override
    public ImageCompetitionDto updateImageCompetition(ImageCompetitionDto ImageCompetitionDto) throws NoSuchAlgorithmException {

        ImageCompetitionMapperHandler imageCompetitionMapperHandler = new ImageCompetitionMapperHandler();
        ImageCompetition imageCompetition = imageCompetitionMapperHandler.createEntityFromDTO(ImageCompetitionDto);

        return imageCompetitionMapperHandler.createDTOFromEntity(imageUploadService.saveOrUpdate(imageCompetition));

    }
}
