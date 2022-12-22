package com.oraclecompany.bbanggle.domain.ceo.constant;

public enum Role {
    ROLE_USER, ROLE_ADMIN;

    public static Role from(String role) {
        return Role.valueOf(role);
    }
}