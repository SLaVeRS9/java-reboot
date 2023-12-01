package ru.sberbank.edu;

import ru.sberbank.edu.caches.WeatherCache;
import ru.sberbank.edu.providers.WeatherProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException {
        System.out.println( "Hello World!" );
        WeatherProvider weatherProvider = new WeatherProvider();
        //WeatherInfo weatherInfo = weatherProvider.get("Moscow");
        WeatherCache weatherCache = new WeatherCache(weatherProvider);
        //WeatherInfo weatherInfo = weatherCache.getWeatherInfo("London");
        //System.out.println(weatherInfo);
        //System.out.println(weatherCache.cache);
        List<String> cities1 = new ArrayList<>();
        List<String> cities2 = new ArrayList<>();
        cities1.add("London");
        cities1.add("Moscow");
        cities1.add("Boston");
        cities1.add("Riga");
        cities1.add("Helsinki");
        cities1.add("London");
        cities1.add("London");

        cities2.add("Moscow");
        cities2.add("London");
        cities2.add("Boston");
        cities2.add("Riga");
        cities2.add("Helsinki");
        cities2.add("Moscow");
        cities2.add("Moscow");

        MultithreadingTest multithreadingTest1 = new MultithreadingTest(weatherProvider, weatherCache);
        multithreadingTest1.setCities(cities1);

        MultithreadingTest multithreadingTest2 = new MultithreadingTest(weatherProvider, weatherCache);
        multithreadingTest2.setCities(cities2);

        Thread thread1 = new Thread(multithreadingTest1);
        Thread thread2 = new Thread(multithreadingTest2);

        thread1.start();
        thread2.start();

        System.out.println(Thread.currentThread().getName());
        Thread.sleep(100000);

    }
}
