package com.oraclecompany.bbanggle.domain.ceo.constant;

public enum Role {
    ROLE_USER,  // 일반 사용자
    ROLE_ADMIN; // 관리자

    public static Role from(String role) {
        return Role.valueOf(role);
    }
}