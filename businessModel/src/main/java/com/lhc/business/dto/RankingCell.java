package com.lhc.business.dto;

import lombok.Data;

@Data
public class RankingCell {

    private Integer position;
    private String name;
    private Integer points;

    public RankingCell(Integer position, String name, Integer points) {
        this.position = position;
        this.name = name;
        this.points = points;
    }
}
