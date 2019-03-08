package com.lhc.mapper.mapperHandler;

import com.lhc.datamodel.entities.rules.Rule;
import com.lhc.datamodel.enumeration.Label;
import com.lhc.dto.RuleDto;
import com.lhc.mapper.mapperHandler.RuleMapperHandler;
import org.junit.Test;

import static com.lhc.datamodel.entities.rules.Rule.rule;
import static org.assertj.core.api.Assertions.assertThat;


public class RuleMapperHandlerTest {

    public Rule createRule(){
        return rule(Label.POINT_VOTE,1, 1);
    }


    @Test
    public void createDTOFromEntity() {

        Rule rule = createRule();
        RuleMapperHandler ruleMapperHandler = new RuleMapperHandler();
        RuleDto actual = ruleMapperHandler.createDTOFromEntity(rule);

        assertThat(actual.getLabel().getName()).isNotEmpty();
        assertThat(actual.getIndication()).isEqualTo(rule.getIndication());
    }
}