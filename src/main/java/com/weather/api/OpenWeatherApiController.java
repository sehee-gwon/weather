package com.weather.api;

import com.weather.api.domain.OpenWeatherApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;

@Slf4j
@Controller
@RequestMapping("/api/open-weather")
public class OpenWeatherApiController {
    private static final String baseUrl = "http://api.openweathermap.org/data/2.5/weather";
    private static final String apiKey = "ef793455d9c2b630991a32d738d2fbb7";

    @GetMapping("/data")
    public void data(String city) { // city 를 어떻게 받을까 ?
        StringBuilder urlBuilder = new StringBuilder(baseUrl);

        try {
            urlBuilder.append("?" + URLEncoder.encode("q", "UTF-8") + "=" + city);
            urlBuilder.append("&" + URLEncoder.encode("appid", "UTF-8") + "=" + apiKey);
            urlBuilder.append("&" + URLEncoder.encode("lang", "UTF-8") + "=kr");
            urlBuilder.append("&" + URLEncoder.encode("units", "UTF-8") + "=metric");

            RestTemplate restTemplate = new RestTemplate();
            OpenWeatherApi response = restTemplate.getForObject(urlBuilder.toString(), OpenWeatherApi.class);

            log.info("OpenWeatherApi Data : {}", city);
        } catch (Exception e) {
            // Exception e
            log.info("OpenWeatherApi Error : {}", e.getMessage());
        }
    }
}
