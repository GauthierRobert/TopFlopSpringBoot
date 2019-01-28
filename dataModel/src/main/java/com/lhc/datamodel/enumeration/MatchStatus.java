
package com.lhc.datamodel.enumeration;

public enum MatchStatus {

    OPEN(1),
    ON_HOLD(2),
    CLOSED(3);

    private int value;

    MatchStatus(int value) {
        this.value = value;

    }

    public int getValue() {
        return value;
    }


}
