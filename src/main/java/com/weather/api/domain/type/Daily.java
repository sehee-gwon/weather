package com.weather.api.domain.type;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Daily {
    @JsonProperty("dt")
    private int dt;                 // 예측 된 데이터의 시간, 유닉스, UTC

    @JsonProperty("sunrise")
    private int sunrise;            // 일출 시간, 유닉스, UTC

    @JsonProperty("sunset")
    private int sunset;             // 일몰 시간, 유닉스, UTC

    @JsonProperty("moonrise")
    private int moonrise;           // 달이 오늘 상승하는 시간, 유닉스, UTC

    @JsonProperty("moonset")
    private int moonset;            // 달이 오늘을 위해 설정하는 시간, 유닉스, UTC

    @JsonProperty("moon_phase")
    private double moonPhase;        // 달 단계. '새달'은 '1분기 달'이며 '보름달'이며 '마지막 분기 달'입니다.

    @JsonProperty("temp")
    private Temp temp;              // 온도. 단위 – 섭씨

    @JsonProperty("feels_like")
    private FeelsLike feelsLike;    // 체감온도. 단위 – 섭씨

    @JsonProperty("pressure")
    private int pressure;           // 해수면에 대기압, hPa

    @JsonProperty("humidity")
    private int humidity;           // 습도, %

    @JsonProperty("dew_point")
    private double dewPoint;         // 물방울이 응축되기 시작하고 이슬이 형성 될 수있는 아래 대기 온도 (압력과 습도에 따라 변화). 단위 – 섭씨

    @JsonProperty("wind_speed")
    private double windSpeed;        // 풍속. 단위 – m/s

    @JsonProperty("wind_deg")
    private double windDeg;          // 바람의 방향, 도 (기상)

    @JsonProperty("weather")
    private List<Weather> weather;  // 추가 정보 기상 조건 코드

    @JsonProperty("clouds")
    private double clouds;           // 흐림, %

    @JsonProperty("pop")
    private double pop;              // 강수량의 확률

    @JsonProperty("uvi")
    private double uvi;              // 하루 동안 UV 인덱스의 최대 값
}
