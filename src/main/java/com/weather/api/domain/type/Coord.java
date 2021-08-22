package com.weather.api.domain.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coord {
    @JsonProperty("lon")
    private float lon;  // 도시의 지리적 위치, 경도

    @JsonProperty("lat")
    private float lat;  // 도시의 지리적 위치, 위도
}
