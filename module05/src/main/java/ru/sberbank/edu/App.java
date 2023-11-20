package ru.sberbank.edu;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        /*System.out.println( "Hello World!" );
        String latitudeGradus = "32(10'03'')";
        String longitudeGradus = "-180(45'07'')";
        GeoPosition geoPosition = new GeoPosition(latitudeGradus, longitudeGradus);
        System.out.println(geoPosition);*/


        TravelService travelService = new TravelService();
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
        System.out.println(city1.getPosition());
        System.out.println(city2.getPosition());
        int distance = travelService.getDistance("MSK", "SPB");
        //System.out.println(distance);
        System.out.println(travelService.getCitiesNear("MSK", 10000));
        double check = -160 + 45/60 + 07/3600;
        System.out.println(check);
    }
}
