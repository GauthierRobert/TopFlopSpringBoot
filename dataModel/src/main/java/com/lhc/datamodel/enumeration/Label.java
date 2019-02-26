package com.lhc.datamodel.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Label {

    NUMBER_VOTE_TOP(1),
    NUMBER_VOTE_FLOP(2),
    VALIDATION_TOP(4),
    VALIDATION_FLOP(5),
    POINT_VOTE(3);

    private int value;

    Label(int value) {
        this.value = value;

    }

    public int getValue() {
        return value;
    }

    @JsonValue
    public String getName(){
        return this.name();
    }


}
