package com.lhc.business.service.image.impl;

import com.lhc.business.service.competition.CompetitionService;
import com.lhc.business.service.image.ImageUploadService;
import com.lhc.datamodel.entities.competition.Competition;
import com.lhc.datamodel.entities.image.ImageCompetition;
import com.lhc.datamodel.repository.image.ImageCompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class ImageUploadServiceImpl implements ImageUploadService {

    @Autowired
    private ImageCompetitionRepository imageCompetitionRepository;

    @Override
    public ImageCompetition saveOrUpdate(ImageCompetition imageCompetition) {

        String asBase64 = imageCompetition.getAsBase64();
        if(imageCompetition.getCompetition_ref() !=null) {
            imageCompetition = findByCompetition_ref(imageCompetition.getCompetition_ref());
            if(imageCompetition !=null) {
                imageCompetition.setAsBase64(asBase64);
                return imageCompetitionRepository.save(imageCompetition);
            }
        }
        return null;
    }

    @Override
    public ImageCompetition findByCompetition_ref(String competition_ref) {
        return imageCompetitionRepository.findByCompetition_ref(competition_ref);
    }

    @Override
    public String convertToBase64(File file) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] fileContent = new byte[(int)file.length()];;
        fileInputStream.read(fileContent);

        return new String (Base64.getEncoder().encode(fileContent), StandardCharsets.UTF_8);

    }
}
