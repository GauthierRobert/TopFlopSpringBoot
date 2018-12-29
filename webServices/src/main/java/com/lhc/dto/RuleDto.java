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

    public RuleDto() {
    }
}
