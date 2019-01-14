package com.lhc.mapper.rule;

import com.lhc.datamodel.entities.rules.Rule;
import com.lhc.dto.RuleDto;
import com.lhc.mapper.MapperHandler;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class RuleMapperHandler implements MapperHandler<Rule, RuleDto> {

    @Override
    public Rule mapToEntity(RuleDto ruleDto, Rule rule){

        if (rule ==null){
            rule = new Rule();
        }


        MapperFacade mapper = RuleSingletonMapper.getInstanceEntity();
        mapper.map(ruleDto, rule);

        return rule;

    }

    @Override
    public Rule createEntityFromDTO(RuleDto ruleDto) {
        return mapToEntity(ruleDto, new Rule());
    }

    @Override
    public RuleDto createDTOFromEntity(Rule rule) {
        return mapToDto(rule, new RuleDto());
    }

    @Override
    public RuleDto mapToDto(Rule rule, RuleDto ruleDto){

        if (ruleDto ==null){
            ruleDto = new RuleDto();
        }

        MapperFacade mapper = RuleSingletonMapper.getInstanceDto();
        mapper.map(ruleDto, rule);

        return ruleDto;
    }

}
