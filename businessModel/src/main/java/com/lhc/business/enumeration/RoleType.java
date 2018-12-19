package com.lhc.business.enumeration;

public enum RoleType {
    ROLE_ADMIN(new Long(1)),
    ROLE_USER(new Long(2))
    ;

    private Long value;

    RoleType(Long value) {
        this.value = value;

    }

    public Long getValue() {
        return value;
    }
}
