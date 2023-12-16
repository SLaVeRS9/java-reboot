package ru.sberbank.edu.caches;

import org.springframework.stereotype.Component;
import ru.sberbank.edu.dto.WeatherInfo;
import ru.sberbank.edu.interfaces.WeatherCache;
import ru.sberbank.edu.interfaces.WeatherProvider;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Weather cache.
 */
@Component
public class WeatherCacheImpl implements WeatherCache {

    private final Map<String, WeatherInfo> cache = new HashMap<>();
    private WeatherProvider weatherProvider;

    /**
     * Default constructor.
     */
    public WeatherCacheImpl(WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
    }

    /**
     * Get ACTUAL weather info for current city or null if current city not found.
     * If cache doesn't contain weather info OR contains NOT ACTUAL info then we should download info
     * If you download weather info then you should set expiry time now() plus 5 minutes.
     * If you can't download weather info then remove weather info for current city from cache.
     *
     * @param city - city
     * @return actual weather info
     */
    @Override
    public WeatherInfo getWeatherInfo(String city) {
        // should be implemented
        WeatherInfo weatherInfo;
        if(cache.containsKey(city)) {
            weatherInfo = cache.get(city);
            if (LocalDateTime.now().isAfter(weatherInfo.getExpiryTime())) {
                try {
                    WeatherInfo actualWeatherInfo = weatherProvider.get(city);
                    cache.put(city, actualWeatherInfo);
                } catch (Exception e) {
                    removeWeatherInfo(city);
                    System.out.println("Can't update weather information. Old information has been removed");
                    e.printStackTrace();
                }
            } else {
                return cache.get(city);
            }
        } else {
            try {
                weatherInfo = weatherProvider.get(city);
                cache.put(city, weatherInfo);
            } catch (Exception e) {
                System.out.println("Can't download weather information");
                e.printStackTrace();
                return null;
            }

        }
        return weatherInfo;
    }

    /**
     * Remove weather info from cache.
     **/
    @Override
    public void removeWeatherInfo(String city) {
        cache.remove(city);
    }
}