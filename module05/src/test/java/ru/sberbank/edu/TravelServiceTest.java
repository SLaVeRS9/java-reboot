package ru.sberbank.edu;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TravelServiceTest {
    static TravelService travelService;
    private static final String CITY_EXIST = "city already exists";
    private static final String CITY_NOT_EXIST = "city isn't exists";
    @BeforeAll
    static void initAll() {
        travelService = new TravelService();
    }

    @BeforeEach
    void init() {
        CityInfo city1 = new CityInfo("MSK", new GeoPosition("77(09'14'')", "-139(23'53'')"));
        CityInfo city2 = new CityInfo("SPB", new GeoPosition("-77(10'49'')", "-139(33'00'')"));
        CityInfo city3 = new CityInfo("EKB", new GeoPosition("60(10'03'')", "-90(45'07'')"));
        CityInfo city4 = new CityInfo("TVER", new GeoPosition("77(09'14'')", "-139(23'54'')"));
        CityInfo city5 = new CityInfo("DACHA", new GeoPosition("77(08'15'')", "-139(23'54'')"));

        travelService.add(city1);
        travelService.add(city2);
        travelService.add(city3);
        travelService.add(city4);
        travelService.add(city5);

    }

    @Test
    void addIfNotContain() {
        CityInfo city6 = new CityInfo("DACHA_2", new GeoPosition("77(08'15'')", "-139(23'54'')"));
        travelService.add(city6);
        assertTrue(travelService.getCities().contains(city6));
    }

    @Test
    void addIfContain() {
        CityInfo city6 = new CityInfo("DACHA", new GeoPosition("77(08'15'')", "-139(23'54'')"));
        travelService.add(city6);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> travelService.add(city6));
        assertEquals(CITY_EXIST, ex.getMessage());
    }

    @Test
    void removeIfNotContain() {
        CityInfo city6 = new CityInfo("DACHA_2", new GeoPosition("77(08'15'')", "-139(23'54'')"));
        Exception ex = assertThrows(IllegalArgumentException.class, () -> travelService.remove(city6.getName()));
        assertEquals(CITY_NOT_EXIST, ex.getMessage());
    }

    @Test
    void removeIfContain() {
        CityInfo city6 = new CityInfo("DACHA_2", new GeoPosition("77(08'15'')", "-139(23'54'')"));
        travelService.add(city6);
        travelService.remove(city6.getName());
        assertFalse(travelService.getCities().contains(city6));
    }

    @Test
    void getDistance() {
        CityInfo city1 = new CityInfo("MSK", new GeoPosition("77(09'14'')", "-139(23'53'')"));
        CityInfo city2 = new CityInfo("SPB", new GeoPosition("-77(10'49'')", "-139(33'00'')"));
        assertEquals(17166005, travelService.getDistance(city1.getName(), city2.getName()));
    }

    @Test
    void getCitiesNear() {
        List<String> citiesNear = travelService.getCitiesNear("MSK", 10000);
        assertTrue(citiesNear.contains("DACHA"));
        assertTrue(citiesNear.contains("TVER"));
        assertFalse(citiesNear.contains("SPB"));
    }

    @AfterAll
    static void tearDownAll() {
        travelService = null;
    }
}
