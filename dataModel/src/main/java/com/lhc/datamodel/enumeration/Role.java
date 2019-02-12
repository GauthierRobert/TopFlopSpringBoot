package com.lhc.datamodel.enumeration;

public enum Role {
    ROLE_ADMIN(1L),
    ROLE_USER(2L)
    ;

    private Long value;

    Role(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
}
