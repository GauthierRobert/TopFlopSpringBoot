package com.lhc.business.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Ballot {

    private String reference;

    private List<Vote> votes;

    public Ballot() {
        this.votes = new ArrayList<>();
    }
}
