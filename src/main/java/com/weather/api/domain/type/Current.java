package com.weather.api.domain.type;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Current {
    @JsonProperty("dt")
    private int dt;                 // 현재 시간, 유닉스, UTC

    @JsonProperty("sunrise")
    private int sunrise;            // 일출 시간, 유닉스, UTC

    @JsonProperty("sunset")
    private int sunset;             // 일몰 시간, 유닉스, UTC

    @JsonProperty("temp")
    private double temp;             // 온도. 단위 - 섭씨

    @JsonProperty("feels_like")
    private double feelsLike;        // 체감온도. 단위 - 섭씨

    @JsonProperty("pressure")
    private double pressure;           // 해수면에 대기압, hPa

    @JsonProperty("humidity")
    private float humidity;           // 습도, %

    @JsonProperty("dew_point")
    private double dewPoint;         // 물방울이 응축되기 시작하고 이슬이 형성 될 수있는 아래 대기 온도. 단위 - 섭씨

    @JsonProperty("uvi")
    private double uvi;              // 현재 UV 인덱스

    @JsonProperty("clouds")
    private double clouds;             // 흐림, %

    @JsonProperty("visibility")
    private int visibility;         // 평균 시야, 미터

    @JsonProperty("wind_speed")
    private double windSpeed;          // 풍속. 풍속. 단위 - m/s

    @JsonProperty("wind_deg")
    private double windDeg;            // 바람의 방향, 도 (기상)

    @JsonProperty("weather")
    private List<Weather> weather;  // 추가 정보 기상 조건 코드
}
