package ru.sberbank.edu;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        String latitudeGradus = "32(10'03'')";
        String longitudeGradus = "-180(45'07'')";
        GeoPosition geoPosition = new GeoPosition(latitudeGradus, longitudeGradus);
        System.out.println(geoPosition);
    }
}
