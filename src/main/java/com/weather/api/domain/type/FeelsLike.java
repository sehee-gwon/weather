package com.weather.api.domain.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeelsLike {
    @JsonProperty("day")
    private double day;      // 낮 기온.

    @JsonProperty("night")
    private double night;    // 밤 온도.

    @JsonProperty("eve")
    private double eve;      // 저녁 온도.

    @JsonProperty("morn")
    private double morn;     // 아침 온도.
}
