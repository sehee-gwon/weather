package com.weather.api.domain.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rain {
    @JsonProperty("1h")
    private float rain1h;   // 지난 1 시간 동안의 강우량, mm

    @JsonProperty("3h")
    private float rain3h;   // 지난 3 시간 동안의 강우량, mm
}
