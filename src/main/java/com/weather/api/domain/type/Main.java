package com.weather.api.domain.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Main {
    @JsonProperty("temp")
    private float temp;         // 온도. 단위 섭씨

    @JsonProperty("feels_like")
    private float feelsLike;    // 체감온도. 단위 섭씨

    @JsonProperty("pressure")
    private float pressure;     // 기압

    @JsonProperty("humidity")
    private float humidity;     // 습도, %

    @JsonProperty("temp_min")
    private float tempMin;      // 현재 최저 온도.(대규모 대도시 및 도시 지역 내)

    @JsonProperty("temp_max")
    private float tempMax;      // 현재 최대 온도.(대규모 대도시 및 도시 지역 내)

    @JsonProperty("sea_level")
    private float seaLevel;     // 해수면의 대기압, hPa

    @JsonProperty("grnd_level")
    private float grandLevel;   // 지면의 대기압, hPa
}
