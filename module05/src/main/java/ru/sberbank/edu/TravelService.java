package ru.sberbank.edu;

import java.util.ArrayList;
import java.util.List;

/**
 * Travel Service.
 */
public class TravelService {

    // do not change type
    private final List<CityInfo> cities = new ArrayList<>();

    /**
     * Append city info.
     *
     * @param cityInfo - city info
     * @throws IllegalArgumentException if city already exists
     */
    public void add(CityInfo cityInfo) {
        if(cities.contains(cityInfo)) {
            throw new IllegalArgumentException("city already exists");
        }
        cities.add(cityInfo);
    }

    /**
     * remove city info.
     *
     * @param cityName - city name
     * @throws IllegalArgumentException if city doesn't exist
     */
    public void remove(String cityName) {
        boolean isNotCityExistInList = cities.stream().noneMatch(cn -> cn.getName().equals(cityName));
        if (isNotCityExistInList) {
            throw new IllegalArgumentException("city doesn't not exists");
        }
        List<CityInfo> city = cities.stream().filter(cityInfo -> cityInfo.getName().equals(cityName)).toList();
        cities.removeAll(city);
    }

    /**
     * Get cities names.
     */
    public List<String> citiesNames() {
        return cities.stream().map(CityInfo::getName).toList();
    }

    /**
     * Get distance in kilometers between two cities.
     * https://www.kobzarev.com/programming/calculation-of-distances-between-cities-on-their-coordinates/
     *
     * @param srcCityName  - source city
     * @param destCityName - destination city
     * @throws IllegalArgumentException if source or destination city doesn't exist.
     */
    public int getDistance(String srcCityName, String destCityName) {
        return 0;
    }

    /**
     * Get all cities near current city in radius.
     *
     * @param cityName - city
     * @param radius   - radius in kilometers for search
     * @throws IllegalArgumentException if city with cityName city doesn't exist.
     */
    public List<String> getCitiesNear(String cityName, int radius) {
        return null;
    }
}
