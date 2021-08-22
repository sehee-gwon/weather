package com.weather.api.domain.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Snow {
    @JsonProperty("1h")
    private float snow1h;   // 지난 1 시간 동안의 눈량, mm

    @JsonProperty("3h")
    private float snow3h;   // 지난 3 시간 동안의 눈량, mm
}
