package com.lhc.datamodel.enumeration;

public enum VoteType {

    TOP(1),
    FLOP(2);

    private int value;

    VoteType(int value) {
        this.value = value;

    }

    public int getValue() {
        return value;
    }


}
