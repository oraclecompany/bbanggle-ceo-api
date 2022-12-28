package com.oraclecompany.bbanggle.domain.product.constant;

public enum DayType {
    AM("오전"),
    PM("오후");

    private final String description;

    DayType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
