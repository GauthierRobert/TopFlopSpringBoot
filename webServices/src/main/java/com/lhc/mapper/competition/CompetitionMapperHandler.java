package com.lhc.mapper.competition;

import com.lhc.datamodel.entities.Competition;
import com.lhc.dto.CompetitionDto;
import com.lhc.mapper.MapperHandler;
import com.lhc.mapper.rule.RuleMapperHandler;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

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
        return competitionDto;

    }

    
    
   


}
