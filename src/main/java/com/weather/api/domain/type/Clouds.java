package com.weather.api.domain.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Clouds {
    @JsonProperty("all")
    private int all;    // 흐림, %
}
