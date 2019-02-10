package com.lhc.datamodel.entities.competition.manyToMany;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserCompetitionId implements Serializable {

    private Long user;

    private Long competition;

}
