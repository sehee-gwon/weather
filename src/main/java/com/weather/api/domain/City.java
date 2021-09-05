package com.weather.api.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class City {
    private String code;
    private double lat;
    private double lon;
}
