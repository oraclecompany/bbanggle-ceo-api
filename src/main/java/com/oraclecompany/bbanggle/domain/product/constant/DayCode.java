package com.oraclecompany.bbanggle.domain.product.constant;

public enum DayCode {
    MON("월요일"),
    TUE("화요일"),
    WED("수요일"),
    THU("목요일"),
    FRI("금요일"),
    SAT("토요일"),
    SUN("일요일"),
    WKD("평일"),
    WKE("주말"),
    ALW("상시");

    private final String description;

    DayCode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
