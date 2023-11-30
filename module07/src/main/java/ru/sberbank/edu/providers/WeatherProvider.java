package ru.sberbank.edu.providers;

import org.springframework.web.client.RestTemplate;
import ru.sberbank.edu.models.WeatherInfo;

public class WeatherProvider {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}";
    private static final String API_KEY = "783533d3c74d7ae17f762827a0f7ef08";

    /**
     * Download ACTUAL weather info from internet.
     * You should call GET http://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
     * You should use Spring Rest Template for calling requests
     *
     * @param city - city
     * @return weather info or null
     */
    public WeatherInfo get(String city) {
        try {
            WeatherInfo resp = restTemplate.getForObject(WEATHER_URL, WeatherInfo.class, city, API_KEY);
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
