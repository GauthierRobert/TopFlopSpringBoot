package com.lhc.mapper.mapperHandler;

import com.lhc.datamodel.entities.rules.Rule;
import com.lhc.dto.RuleDto;
import com.lhc.mapper.MapperHandler;
import com.lhc.mapper.singletonMapper.RuleSingletonMapper;
import ma.glasnost.orika.MapperFacade;

import static com.lhc.datamodel.entities.rules.Rule.rule;
import static com.lhc.dto.RuleDto.ruleDto;

public class RuleMapperHandler implements MapperHandler<Rule, RuleDto> {

    @Override
    public Rule mapToEntity(RuleDto ruleDto, Rule rule){

        if (rule ==null){
            rule = rule();
        }


        MapperFacade mapper = RuleSingletonMapper.getInstanceEntity();
        mapper.map(ruleDto, rule);

        return rule;

    }

    @Override
    public Rule createEntityFromDTO(RuleDto ruleDto) {
        return mapToEntity(ruleDto, rule());
    }

    @Override
    public RuleDto createDTOFromEntity(Rule rule) {
        return mapToDto(rule, ruleDto());
    }

    @Override
    public RuleDto mapToDto(Rule rule, RuleDto ruleDto){

        if (ruleDto ==null){
            ruleDto = ruleDto();
        }

        MapperFacade mapper = RuleSingletonMapper.getInstanceDto();
        mapper.map(rule, ruleDto);

        return ruleDto;
    }

}
