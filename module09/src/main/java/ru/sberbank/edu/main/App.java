package ru.sberbank.edu.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sberbank.edu.dto.WeatherInfo;
import ru.sberbank.edu.config.ProjectConfig;
import ru.sberbank.edu.interfaces.WeatherCache;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        WeatherCache cache = context.getBean(WeatherCache.class);
        WeatherInfo weatherInfo = cache.getWeatherInfo("Omsk");
        System.out.println("GOOD! weather=" + weatherInfo);
    }
}
