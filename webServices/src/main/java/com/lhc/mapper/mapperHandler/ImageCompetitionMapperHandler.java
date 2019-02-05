package com.lhc.mapper.mapperHandler;

import com.lhc.datamodel.entities.image.ImageCompetition;
import com.lhc.dto.ImageCompetitionDto;
import com.lhc.mapper.MapperHandler;
import com.lhc.mapper.singletonMapper.ImageCompetitionSingletonMapper;
import ma.glasnost.orika.MapperFacade;

import static com.lhc.datamodel.entities.image.ImageCompetition.imageCompetition;

public class ImageCompetitionMapperHandler implements MapperHandler<ImageCompetition, ImageCompetitionDto> {

    @Override
    public ImageCompetition mapToEntity(ImageCompetitionDto imageCompetitionDto, ImageCompetition imageCompetition) {

        if (imageCompetition == null){
            imageCompetition = ImageCompetition.imageCompetition();
        }

        MapperFacade mapper = ImageCompetitionSingletonMapper.getInstanceEntity();
        mapper.map(imageCompetitionDto, imageCompetition);

        return imageCompetition;
    }

    @Override
    public ImageCompetition createEntityFromDTO(ImageCompetitionDto imageCompetitionDto) {
        return mapToEntity(imageCompetitionDto, imageCompetition());
    }

    @Override
    public ImageCompetitionDto createDTOFromEntity(ImageCompetition imageCompetition) {
        return mapToDto(imageCompetition, new ImageCompetitionDto());
    }


    @Override
    public ImageCompetitionDto mapToDto(ImageCompetition imageCompetition, ImageCompetitionDto imageCompetitionDto) {

        if (imageCompetitionDto == null) {
            imageCompetitionDto = new ImageCompetitionDto();
        }

        MapperFacade mapper = ImageCompetitionSingletonMapper.getInstanceDto();
        mapper.map(imageCompetition, imageCompetitionDto);

        return imageCompetitionDto;
    }

}
