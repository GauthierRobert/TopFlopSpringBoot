package com.lhc.datamodel.enumeration;

public enum SportType {

    HOCKEY(1L),
    ICE_HOCKEY(2L),
    FOOTBALL(3L),
    BACKETBALL(4L),
    FUTSAL(5L),
    HANDBALL(6L),
    RUGBY(7L),
    AMERICAN_FOOTBALL(8L)
    ;

    private Long value;

    SportType(Long value) {
        this.value = value;

    }

    public Long getValue() {
        return value;
    }


}
