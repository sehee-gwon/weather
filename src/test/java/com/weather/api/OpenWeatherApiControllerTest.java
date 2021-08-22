package com.weather.api;

import com.weather.api.domain.OpenWeatherApi;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URLEncoder;

@SpringBootTest
class OpenWeatherApiControllerTest {

    private static final String baseUrl = "http://api.openweathermap.org/data/2.5/weather";
    private static final String apiKey = "ef793455d9c2b630991a32d738d2fbb7";

    @Test
    public void data() {
        StringBuilder urlBuilder = new StringBuilder(baseUrl);
        String city = "Seoul";

        try {
            urlBuilder.append("?" + URLEncoder.encode("q", "UTF-8") + "=" + city);
            urlBuilder.append("&" + URLEncoder.encode("appid", "UTF-8") + "=" + apiKey);
            urlBuilder.append("&" + URLEncoder.encode("lang", "UTF-8") + "=kr");
            urlBuilder.append("&" + URLEncoder.encode("units", "UTF-8") + "=metric");

            RestTemplate restTemplate = new RestTemplate();
            OpenWeatherApi response = restTemplate.getForObject(urlBuilder.toString(), OpenWeatherApi.class);



            System.out.println(response);
        } catch (Exception e) {
            // Exception e
            System.out.println(e.getMessage());
        }
    }
}