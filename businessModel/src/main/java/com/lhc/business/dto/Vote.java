package com.lhc.business.dto;


import lombok.Data;

@Data
public class Vote {

    private String reference;

    // 1 for first top vote
    // 2 for second top vote
    // ...
    // -1 for first flop vote
    // -2 for second flop vote
    private Integer index;

    private String name;

    private Integer points;


}
