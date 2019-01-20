package com.lhc.mapper.rule;

import com.lhc.datamodel.entities.rules.Rule;
import com.lhc.datamodel.enumeration.LabelType;
import com.lhc.dto.RuleDto;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static com.lhc.datamodel.entities.rules.Rule.rule;
import static org.assertj.core.api.Assertions.assertThat;


public class RuleMapperHandlerTest {

    public Rule createRule(){
        return rule(LabelType.POINT_VOTE.name(),1, 1);
    }


    @Test
    public void createDTOFromEntity() {

        Rule rule = createRule();
        RuleMapperHandler ruleMapperHandler = new RuleMapperHandler();
        RuleDto actual = ruleMapperHandler.createDTOFromEntity(rule);

        assertThat(actual.getLabel()).isNotEmpty();
        assertThat(actual.getIndication()).isEqualTo(rule.getIndication());
    }
}