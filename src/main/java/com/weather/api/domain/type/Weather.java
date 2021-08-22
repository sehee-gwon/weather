package com.weather.api.domain.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Weather {
    @JsonProperty("id")
    private int id;             // 기상 조건 ID

    @JsonProperty("main")
    private String main;        // 날씨 매개 변수 그룹 (비, 눈, 극한 등)

    @JsonProperty("description")
    private String description; // 그룹 내 날씨 조건

    @JsonProperty("icon")
    private String icon;        // 날씨 아이콘 ID
}
