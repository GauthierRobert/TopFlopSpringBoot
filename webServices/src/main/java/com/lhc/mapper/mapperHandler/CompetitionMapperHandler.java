package com.lhc.mapper.mapperHandler;

import com.lhc.datamodel.entities.competition.Competition;
import com.lhc.dto.CompetitionDto;
import com.lhc.mapper.MapperHandler;
import com.lhc.mapper.singletonMapper.CompetitionSingletonMapper;
import ma.glasnost.orika.MapperFacade;

import static com.lhc.datamodel.entities.image.ImageCompetition.imageCompetition;

public class CompetitionMapperHandler implements MapperHandler<Competition, CompetitionDto> {

    @Override
    public Competition mapToEntity(CompetitionDto competitionDto, Competition competition) {
        if (competition ==null){
            competition = Competition.competition();
        }

        MapperFacade mapper = CompetitionSingletonMapper.getInstanceEntity();
        mapper.map(competitionDto, competition);
        
        RuleMapperHandler ruleMapperHandler = new RuleMapperHandler();
        if (competitionDto.getRuleDtos() !=null) {
            competition.setRules(ruleMapperHandler.mapToListEntities(competitionDto.getRuleDtos()));
        }

        if(competitionDto.getImageAsBase64() !=null){
            competition.setImageCompetition(imageCompetition(competitionDto.getImageAsBase64(), competitionDto.getSystemData().getReference(), competition));
        }

        return competition;

    }

    @Override
    public Competition createEntityFromDTO(CompetitionDto competitionDto) {
        return mapToEntity(competitionDto, Competition.competition());
    }

    @Override
    public CompetitionDto createDTOFromEntity(Competition competition) {
        return mapToDto(competition, new CompetitionDto());
    }

    @Override
    public CompetitionDto mapToDto(Competition competition, CompetitionDto competitionDto) {

        if (competitionDto ==null){
            competitionDto = new CompetitionDto();
        }

        MapperFacade mapper = CompetitionSingletonMapper.getInstanceDto();
        mapper.map(competition, competitionDto);

        RuleMapperHandler ruleMapperHandler = new RuleMapperHandler();
        if (competition.getRules() !=null) {
            competitionDto.setRuleDtos(ruleMapperHandler.mapToListDtos(competition.getRules()));
        }

        if(competition.getImageCompetition() !=null){
            competitionDto.setImageAsBase64(competition.getImageCompetition().getAsBase64());
        }
        return competitionDto;

    }

    
    
   


}
