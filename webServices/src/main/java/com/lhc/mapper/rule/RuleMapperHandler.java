package com.lhc.business.service.mapper.rule;

public class RuleMapperHandler implements MapperHandler<Rule, RuleRecord> {

    @Override
    public RuleRecord mapRecord(Rule rule, RuleRecord ruleRecord){

        if (ruleRecord ==null){
            ruleRecord = new RuleRecord();
        }

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Rule.class, RuleRecord.class)
                .field("description", "description")
                .field("label", "label")
                .field("points", "points")
                .register();

        MapperFacade mapper = mapperFactory.getMapperFacade();

        mapper.map(rule, ruleRecord);

        return ruleRecord;

    }

    @Override
    public Rule map(RuleRecord ruleRecord, Rule rule){

        if (rule ==null){
            rule = new Rule();
        }

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(RuleRecord.class, Rule.class)
                .field("description", "description")
                .field("label", "label")
                .field("points", "points")
                .register();

        MapperFacade mapper = mapperFactory.getMapperFacade();

        mapper.map(ruleRecord, rule);

        return rule;
    }

}
