package com.weather.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.weather.api.domain.type.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * OpenWeatherAPI - 현재 날씨 데이터
 * @author hojun
 */
@Getter
@Setter
public class OpenWeatherApi {
    @JsonProperty("lat")
    private double lat;          // 위치의 지리적 좌표(위도)

    @JsonProperty("lon")
    private double lon;          // 위치의 지리적 좌표(경도)

    @JsonProperty("timezone")
    private String timezone;    // 요청된 위치의 표준 시간대 이름

    @JsonProperty("timezone-offset")
    private int timezoneOffset; // UTC에서 초 단위로 이동

    @JsonProperty("current")
    private Current current;    // 현재 날씨 데이터 API 응답

    @JsonProperty("daily")
    private List<Daily> daily;        // 일일 일기 예보 일기 데이터 API 응답


/*
    @JsonProperty("coord")
    private Coord coord;            // 도시의 경도, 위도 정보

    @JsonProperty("weather")
    private List<Weather> weather;  // 추가 정보 기상 조건 코드

    @JsonProperty("base")
    private String base;            // 내부 매개변수

    @JsonProperty("main")
    private Main main;              // 온도, 기압, 습도 등 정보

    @JsonProperty("wind")
    private Wind wind;              // 풍향, 풍속 관련 정보

    @JsonProperty("clouds")
    private Clouds clouds;          // 구름 정보 (흐림)

    @JsonProperty("rain")
    private Rain rain;              // 강우(비) 정보

    @JsonProperty("snow")
    private Snow snow;              // 눈 정보

    @JsonProperty("dt")
    private long dt;                // 데이터 계산 시간, 유닉스, UTC

    @JsonProperty("sys")
    private Sys sys;                // 기타 정보

    @JsonProperty("timezone")
    private int timezone;           // UTC 에서 초 단위로 이동

    @JsonProperty("id")
    private long id;                // 도시 ID

    @JsonProperty("name")
    private String name;            // 도시 이름

    @JsonProperty("cod")
    private int cod;                // 내부 매개 변수

    @JsonProperty("visibility")
    private int visibility;         // 가시성
*/

}

