package com.lhc.business.enumeration;

public enum LabelType {

    NUMBER_VOTE(1),
    POINT_VOTE(2);

    private int value;

    LabelType(int value) {
        this.value = value;

    }

    public int getValue() {
        return value;
    }


}
