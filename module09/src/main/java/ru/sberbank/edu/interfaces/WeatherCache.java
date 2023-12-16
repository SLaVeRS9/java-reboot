package ru.sberbank.edu.interfaces;

import ru.sberbank.edu.dto.WeatherInfo;

public interface WeatherCache {
    public WeatherInfo getWeatherInfo(String city);
    public void removeWeatherInfo(String city);
}
