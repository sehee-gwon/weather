package com.weather.api.domain.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sys {
    @JsonProperty("type")
    private int type;

    @JsonProperty("id")
    private int id;

    @JsonProperty("country")
    private String country; // 국가 코드 (GB, JP 등)

    @JsonProperty("sunrise")
    private long sunrise;   // 일출 시간, 유닉스, UTC

    @JsonProperty("sunset")
    private long sunset;    // 일몰 시간, 유닉스, UTC
}
