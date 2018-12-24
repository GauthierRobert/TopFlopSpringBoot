package com.lhc.business.service.mapper.competition;

public class CompetitionMapperHandler implements MapperHandler<Competition, CompetitionRecord> {



    @Override
    public Competition mapToEntity(CompetitionDto competitionDto, Competition competition) {
        if (competition ==null){
            competition = new Competition();
        }

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(CompetitionDto.class, Competition.class)
                .field("reference", "reference")
                .field("name", "name")
                .field("password", "password")
                .register();

        MapperFacade mapper = mapperFactory.getMapperFacade();

        mapper.map(competitionDto, competition);

        return competition;

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

        return competitionDto;

    }


}
