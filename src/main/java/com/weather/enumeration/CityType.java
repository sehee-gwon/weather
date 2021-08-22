package com.weather.enumeration;

public enum CityType {
    SEOUL("Seoul", "서울시"),
    INCHEON("Incheon", "인천시"),
    BUSAN("Busan", "부산시");

    private String code;
    private String title;

    CityType(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }
}
