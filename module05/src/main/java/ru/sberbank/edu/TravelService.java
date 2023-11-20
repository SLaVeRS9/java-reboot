package ru.sberbank.edu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Travel Service.
 */
public class TravelService {

    // do not change type
    private final List<CityInfo> cities = new ArrayList<>();
    private static final Integer EARTH_RADIUS = 6_372_795;
    private static final Integer METRES_IN_KILOMETRES = 1000;
    private static final String CITY_EXIST = "city already exists";
    private static final String CITY_NOT_EXIST = "city isn't exists";

    /**
     * Append city info.
     *
     * @param cityInfo - city info
     * @throws IllegalArgumentException if city already exists
     */
    public void add(CityInfo cityInfo) throws IllegalArgumentException {
        if(cities.contains(cityInfo)) {
            throw new IllegalArgumentException(CITY_EXIST);
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
            throw new IllegalArgumentException(CITY_NOT_EXIST);
        }
        List<CityInfo> city = cities.stream()
                .filter(cityInfo -> cityInfo.getName().equals(cityName))
                .toList();
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
     * @return distance in metres
     */
    public int getDistance(String srcCityName, String destCityName) {
        GeoPosition srcGeoPosition = cities.stream()
                .filter(city -> city.getName().equals(srcCityName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .getPosition();

        GeoPosition destGeoPosition = cities.stream()
                .filter(city -> city.getName().equals(destCityName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .getPosition();
        double distance = calculateTheDistance(srcGeoPosition, destGeoPosition);
        return (int) distance;
    }

    /**
     * Calculate distance between teo geo positions
     *
     * @param src  - first geo positions
     * @param dest - second geo positions
     * @return distance in metres
     */
    private double calculateTheDistance(GeoPosition src, GeoPosition dest) {
        double srcCosLatitude = Math.cos(src.getLatitude());
        double destCosLatitude = Math.cos(dest.getLatitude());
        double srcSinLatitude = Math.sin(src.getLatitude());
        double destSinLatitude = Math.sin(dest.getLatitude());
        double longitudeDelta = dest.getLongitude() - src.getLongitude();
        double cosLongitudeDelta = Math.cos(longitudeDelta);
        double sinLongitudeDelta = Math.sin(longitudeDelta);

        double y = Math.sqrt(Math.pow(destCosLatitude * sinLongitudeDelta, 2)
                + Math.pow(srcCosLatitude * destSinLatitude - srcSinLatitude * destCosLatitude * cosLongitudeDelta, 2));
        double x = srcSinLatitude * destSinLatitude + srcCosLatitude * destCosLatitude * cosLongitudeDelta;

        double ad = Math.atan2(y, x);

        return ad * EARTH_RADIUS;
    }

    /**
     * Get all cities near current city in radius.
     *
     * @param cityName - city
     * @param radius   - radius in kilometers for search
     * @throws IllegalArgumentException if city with cityName city doesn't exist.
     * @return list names all cities near current city in radius
     */
    public List<String> getCitiesNear(String cityName, int radius) {
        int radiusInMetres = radius * METRES_IN_KILOMETRES;

        return cities.stream()
                .map(CityInfo::getName)
                .filter(name -> getDistance(cityName, name) < radiusInMetres)
                .filter(name -> {
                    int tempDistance = getDistance(cityName, name);
                    return (tempDistance < radiusInMetres) && tempDistance != 0;
                })
                .toList();
    }

    public List<CityInfo> getCities() {
        return cities;
    }
}
