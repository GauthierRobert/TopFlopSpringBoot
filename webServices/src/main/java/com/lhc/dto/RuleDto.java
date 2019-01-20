package com.lhc.dto;

import lombok.Data;

@Data
public class RuleDto {

    private String description;

    private String label;

    private Integer points;

    // 1 for first top vote
    // 2 for second top vote
    // ...
    // -1 for first flop vote
    // -2 for second flop vote
    private Integer indication;

    private RuleDto() {
    }

    private RuleDto(String description, String label, Integer points, Integer indication) {
        this.description = description;
        this.label = label;
        this.points = points;
        this.indication = indication;
    }


    public static  RuleDto ruleDto(String label, Integer points, Integer indication) {
        return new RuleDto(null, label, points, indication);
    }

    public static  RuleDto ruleDto() {
        return new RuleDto();
    }
}
