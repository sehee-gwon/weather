package com.weather.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.MalformedURLException;
import java.net.URL;

@Controller
@RequestMapping("/api/open-weather")
public class OpenWeatherApiController {
    String city;
    String apiKey = "ef793455d9c2b630991a32d738d2fbb7";
    String language = "&lang=kr";
    String units = "&units=metric";

    @GetMapping("/data")
    public String data() throws MalformedURLException {
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + language + units;
        URL u = new URL(url);


        return "";
    }
}
