package com.lhc.business.service.image;

import com.lhc.datamodel.entities.image.ImageCompetition;

import java.io.File;
import java.io.IOException;

public interface ImageUploadService {

    ImageCompetition saveOrUpdate(ImageCompetition imageCompetition);

    ImageCompetition findByCompetitionReference(String competition_ref);

    String convertToBase64(File file) throws IOException;

}
