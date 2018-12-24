package com.lhc.business.service.mapper.competition;

public class CompetitionMapperHandler implements MapperHandler<Competition, CompetitionRecord> {



    @Override
    public Competition map(CompetitionRecord competitionRecord, Competition competition) {
        if (competition ==null){
            competition = new Competition();
        }

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(CompetitionRecord.class, Competition.class)
                .field("reference", "reference")
                .field("name", "name")
                .field("password", "password")
                .register();

        MapperFacade mapper = mapperFactory.getMapperFacade();

        mapper.map(competitionRecord, competition);

        return competition;

    }

    @Override
    public CompetitionRecord mapRecord(Competition competition, CompetitionRecord competitionRecord) {

        if (competitionRecord ==null){
            competitionRecord = new CompetitionRecord();
        }

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Competition.class, CompetitionRecord.class)
                .field("reference", "reference")
                .field("name", "name")
                .field("password", "password")
                .register();

        MapperFacade mapper = mapperFactory.getMapperFacade();

        mapper.map(competition, competitionRecord);

        return competitionRecord;

    }


}
