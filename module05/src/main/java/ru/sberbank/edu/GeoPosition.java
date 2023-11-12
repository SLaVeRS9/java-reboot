package ru.sberbank.edu;

/**
 * Geo position.
 */
public class GeoPosition {

    /**
     * Широта в радианах.
     */
    private final double latitude;

    /**
     * Долгота в радианах.
     */
    private final double longitude;
    /**
     * Exception if format is incorrect (not like 55 or 55(45'07''))
     */
    private static final String INCORRECT_FORMAT_EX = "This format is incorrect, use format like 55 or 55(45'07'')";
    /**
     * Minutes in hour
     */
    private static final double MIN_IN_HOUR = 60;
    /**
     * Seconds in hour
     */
    private static final double SEC_IN_HOUR = 3600;

    /**
     * Ctor.
     *
     * @param latitudeGraduates  - latitude in graduates
     * @param longitudeGraduates - longitude in graduates
     *                        Possible values: 55, 55(45'07''), 59(57'00'')
     */
    public GeoPosition(String latitudeGraduates, String longitudeGraduates) {
        // parse and set latitude and longitude in radian
        double latitudeGraduatesInDec = convertLatitudeGeoGraduatesToDecGraduates(latitudeGraduates);
        double longitudeGraduatesInDec = convertLongitudeGeoGraduatesToDecGraduates(longitudeGraduates);
        latitude = Math.toRadians(latitudeGraduatesInDec);
        longitude = Math.toRadians(longitudeGraduatesInDec);

    }

    /**
     * Converter longitude graduates on geo format to decimal
     */
    private static double convertLongitudeGeoGraduatesToDecGraduates(String longitudeGraduates) throws IllegalArgumentException {
        if(longitudeGraduates.matches("^-?\\d{1,3}$")){
            System.out.println(Integer.parseInt(longitudeGraduates));
            return Integer.parseInt(longitudeGraduates);
        } else if (longitudeGraduates.matches("^-?\\d{1,3}\\(\\d{1,2}'\\d{1,2}''\\)$")) {
            String[] gradMinSecArr = longitudeGraduates.split("\\(|'|\\)");

            Double grad = Double.parseDouble(gradMinSecArr[0]);
            Double min = Double.parseDouble(gradMinSecArr[1]);
            Double sec = Double.parseDouble(gradMinSecArr[2]);

            double decGrad = grad + min/MIN_IN_HOUR + sec/SEC_IN_HOUR;
            return decGrad;
        } else {
            throw new IllegalArgumentException(INCORRECT_FORMAT_EX);
        }
    }

    /**
     * Converter latitude graduates on geo format to decimal
     */
    private static double convertLatitudeGeoGraduatesToDecGraduates(String latitudeGraduates) throws IllegalArgumentException {
        if(latitudeGraduates.matches("^-?\\d{1,2}$")){
            System.out.println(Integer.parseInt(latitudeGraduates));
            return Integer.parseInt(latitudeGraduates);
        } else if (latitudeGraduates.matches("^-?\\d{1,2}\\(\\d{1,2}'\\d{1,2}''\\)$")) {
            return parseStringGraduatesToDecDouble(latitudeGraduates);
        } else {
            throw new IllegalArgumentException(INCORRECT_FORMAT_EX);
        }
    }

    /**
     * Parser latitude graduates on string format to decimal double
     */
    private static double parseStringGraduatesToDecDouble(String graduates) {
        String[] gradMinSecArr = graduates.split("\\(|'|\\)");

        double grad = Double.parseDouble(gradMinSecArr[0]);
        double min = Double.parseDouble(gradMinSecArr[1]);
        double sec = Double.parseDouble(gradMinSecArr[2]);

        double decGrad = grad + min/MIN_IN_HOUR + sec/SEC_IN_HOUR;
        return decGrad;
    }

    // getters and toString

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "GeoPosition{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}