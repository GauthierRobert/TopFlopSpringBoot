package com.lhc.mapper.competition;

import com.lhc.datamodel.entities.Competition;
import com.lhc.dto.CompetitionDto;
import com.lhc.mapper.MapperHandler;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class CompetitionMapperHandler implements MapperHandler<Competition, CompetitionDto> {

    @Override
    public Competition mapToEntity(CompetitionDto competitionDto, Competition competition) {
        if (competition ==null){
            competition = new Competition();
        }

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(CompetitionDto.class, Competition.class)
                .field("reference", "reference")
                .field("name", "name")
                .field("season", "season")
                .field("division", "division")
                .field("status", "status")
                .field("password", "password")
                .field("creatorUsername", "creatorUsername")
                .register();

        MapperFacade mapper = mapperFactory.getMapperFacade();

        mapper.map(competitionDto, competition);
        
        RuleMapperHandler ruleMapperHandler = new RuleMapperHandler();
        competition.setRules(ruleMapperHandler.mapToListEntities(competitionDto.getRuleDtos));
        
        return competition;

    }

    @Override
    public Competition createEntityFromDTO(CompetitionDto competitionDto) {
        return mapToEntity(competitionDto, new Competition());
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

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Competition.class, CompetitionDto.class)
                .field("reference", "reference")
                .field("name", "name")
                .field("password", "password")
                .register();

        MapperFacade mapper = mapperFactory.getMapperFacade();

        mapper.map(competition, competitionDto);
        RuleMapperHandler ruleMapperHandler = new RuleMapperHandler();
        competitionDto.setRuleDtos(ruleMapperHandler.mapToListDtos(competition.getRules));
        return competitionDto;

    }
    
    
   


}
