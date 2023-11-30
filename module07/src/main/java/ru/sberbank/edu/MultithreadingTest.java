package ru.sberbank.edu;

import ru.sberbank.edu.caches.WeatherCache;
import ru.sberbank.edu.providers.WeatherProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for testing WeatherCash realization with multithreading
 */
public class MultithreadingTest implements Runnable {
    WeatherProvider weatherProvider;
    WeatherCache weatherCache;
    List<String> cities = new ArrayList<>();

    MultithreadingTest(WeatherProvider weatherProvider, WeatherCache weatherCache) {
        this.weatherProvider = weatherProvider;
        this.weatherCache = weatherCache;
    }
    public void setCities(List<String> cities) {
        this.cities.addAll(cities);
    }
    @Override
    public void run() {
        System.out.println("Thread start:" + Thread.currentThread().getName());
        for (String city: cities) {
            System.out.println(Thread.currentThread().getName() + " starts search info for city " + city);
            weatherCache.getWeatherInfo(city);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " " + weatherCache.cache);
        }
    }
}
