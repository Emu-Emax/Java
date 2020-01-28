package pl.cedrox.earthquakes.eqpicker;

public class Coordinate {

    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private double latitude;
    private double longitude;
    private static final double EARTH_RADIUS_IN_KM = 6371;

    private double toRadians(double coordinate) {
        return coordinate * Math.PI / 180;
    }

    ;

    public double countDistance(Coordinate otherCoordinate) {

        double latitude_1 = this.toRadians(latitude);
        double longitude_1 = this.toRadians(longitude);
        double latitude_2 = this.toRadians(otherCoordinate.latitude);
        double longitude_2 = this.toRadians(otherCoordinate.longitude);

        double a = Math.pow(Math.sin((latitude_1 - latitude_2) / 2), 2) + Math.cos(latitude_1)
                * Math.cos(latitude_2) * Math.pow(Math.sin((longitude_1 - longitude_2) / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_IN_KM * c;
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass())
            return false;

        return Double.compare(this.latitude, ((Coordinate) obj).latitude) == 0
                && Double.compare(this.longitude, ((Coordinate) obj).longitude) == 0;
    }

    ;
}
