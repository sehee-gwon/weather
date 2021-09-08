package com.weather.api;

import com.weather.api.domain.City;
import com.weather.api.domain.OpenWeatherApi;
import com.weather.enumeration.CityType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/api/open-weather")
public class OpenWeatherApiController {

    private static final String baseUrl = "http://api.openweathermap.org/data/2.5/onecall";
    private static final String apiKey = "ef793455d9c2b630991a32d738d2fbb7";

    @GetMapping("/cityType")
    public ResponseEntity CityType() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (CityType cityType : CityType.values()) {
            Map<String, Object> map = new LinkedHashMap<>();

            map.put("code", cityType.getCode());
            map.put("title", cityType.getTitle());

            map.put("lat", cityType.getlat());
            map.put("lon", cityType.getlon());

            list.add(map);
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/data")
    public ResponseEntity data(City city) {
        StringBuilder urlBuilder = new StringBuilder(baseUrl);

        try {
            /*if (!StringUtils.isEmpty(city.getCode())) {
                urlBuilder.append("?" + URLEncoder.encode("q", "UTF-8") + "=" + city.getCode());
            }*/
            urlBuilder.append("?" + URLEncoder.encode("lat", "UTF-8") + "=" + city.getLat());
            urlBuilder.append("&" + URLEncoder.encode("lon", "UTF-8") + "=" + city.getLon());


            urlBuilder.append("&" + URLEncoder.encode("exclude", "UTF-8") + "=minutely,hourly,alerts");
            urlBuilder.append("&" + URLEncoder.encode("appid", "UTF-8") + "=" + apiKey);
            urlBuilder.append("&" + URLEncoder.encode("lang", "UTF-8") + "=kr");
            urlBuilder.append("&" + URLEncoder.encode("units", "UTF-8") + "=metric");

            RestTemplate restTemplate = new RestTemplate();
            OpenWeatherApi response = restTemplate.getForObject(urlBuilder.toString(), OpenWeatherApi.class);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("OpenWeatherApi Error : {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
