package ru.sberbank.edu.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.edu.dto.WeatherInfo;
import ru.sberbank.edu.interfaces.WeatherProvider;

/**
 * Weather provider
 */
@Component
public class WeatherProviderImpl implements WeatherProvider {
      private final RestTemplate restTemplate;

      @Value("${weather.api_key}")
      private String appKey;

      @Value("${weather.url}")
      private String weatherUrl;

    WeatherProviderImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Download ACTUAL weather info from internet.
     * You should call GET http://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
     * You should use Spring Rest Template for calling requests
     *
     * @param city - city
     * @return weather info or null
     */
    @Override
    public WeatherInfo get(String city) {
        try {
            WeatherInfo resp = restTemplate.getForObject(weatherUrl, WeatherInfo.class, city, appKey);
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
