package com.lhc.business.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BallotDto {

    private String reference;

    private List<VoteDto> voteDtos;

    public BallotDto() {
        this.voteDtos = new ArrayList<>();
    }
}
