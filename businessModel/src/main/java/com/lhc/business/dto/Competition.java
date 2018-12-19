package com.lhc.business.dto;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Competition {

    private String reference;

    private String name;

    private String password;

    private String confirmedPassword;

    private List<Match> matches;

    public Competition() {
        this.matches = new ArrayList<>();
    }
}
