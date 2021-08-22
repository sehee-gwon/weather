package com.weather.api.domain.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Wind {
    @JsonProperty("speed")
    private float speed;    // 바람의 속도. 단위 기본값 : meter/sec, 미터법 : meter/sec, 임페리얼 : miles/hour

    @JsonProperty("deg")
    private int deg;    // 풍향,도 (기상)

    @JsonProperty("gust")
    private float gust; // 바람 돌풍. 단위 기본값 : meter/sec, 미터법 : meter/sec, 임페리얼 : miles/hour
}
