
package com.lhc.datamodel.entities;

public enum MatchStatus {

    OPEN(1),
    CLOSE(2);

    private int value;

    VoteType(int value) {
        this.value = value;

    }

    public int getValue() {
        return value;
    }


}
