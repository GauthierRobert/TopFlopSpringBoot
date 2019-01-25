
package com.lhc.datamodel.enumeration;

public enum MatchStatus {

    OPEN(1),
    CLOSED(2);

    private int value;

    MatchStatus(int value) {
        this.value = value;

    }

    public int getValue() {
        return value;
    }


}
