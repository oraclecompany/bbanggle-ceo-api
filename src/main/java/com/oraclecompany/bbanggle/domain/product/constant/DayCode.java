package com.oraclecompany.bbanggle.domain.product.constant;

import java.util.List;

public enum DayCode {
    MON("월요일", "1"),
    TUE("화요일", "2"),
    WED("수요일", "3"),
    THU("목요일", "4"),
    FRI("금요일", "5"),
    SAT("토요일", "6"),
    SUN("일요일", "7"),
    WKD("평일", "12345"),
    WKE("주말", "67"),
    ALW("상시", "1234567");

    private final String description;
    private final String number;

    DayCode(String description, String number) {
        this.description = description;
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public static List<DayCode> findDayCode(int dayOfWeek) {
        List<DayCode> dayCodes = new java.util.ArrayList<>(List.of());

        for (DayCode dayCode : DayCode.values()) {
            if (dayCode.number.contains(String.valueOf(dayOfWeek))) {
                dayCodes.add(dayCode);
            }
        }

        return dayCodes;
    }

}
