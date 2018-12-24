package com.lhc.business.service.mapper.rule;

public class RuleMapperHandler implements MapperHandler<RuleDto, Rule> {

    @Override
    public Rule maptoEntity(RuleDto ruleDto, Rule rule){

        if (rule ==null){
            rule = new Rule();
        }

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(RuleDto.class, Rule.class)
                .field("description", "description")
                .field("label", "label")
                .field("points", "points")
                .register();

        MapperFacade mapper = mapperFactory.getMapperFacade();

        mapper.map(ruleDto, rule);

        return rule;

    }

    @Override
    public RuleDto map(Rule rule, RuleDto ruleDto){

        if (ruleDto ==null){
            ruleDto = new RuleDto();
        }

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Rule.class, RuleDto.class)
                .field("description", "description")
                .field("label", "label")
                .field("points", "points")
                .register();

        MapperFacade mapper = mapperFactory.getMapperFacade();

        mapper.map(rule, ruleDto);

        return ruleDto;
    }

}
