package ru.sberbank.edu.interfaces;

import ru.sberbank.edu.dto.WeatherInfo;

public interface WeatherProvider {
    public WeatherInfo get(String city);
}
