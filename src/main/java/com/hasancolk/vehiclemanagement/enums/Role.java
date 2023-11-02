package com.hasancolk.vehiclemanagement.enums;

public enum Role {

    ADMIN(1),
    STANDARD(2);

    private final Integer value;

    Role(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static Role fromValue(Integer value) {
        if (value == null) {
            throw new IllegalArgumentException("Role cannot be null!");
        }
        for (Role role : values()) {
            if (role.getValue().equals(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role value: " + value);
    }

}
