package pl.cedrox.earthquakes.eqpicker;

public class EarthquakeRecord {

    public EarthquakeRecord(Coordinate coordinate, double distance, String title) {
        this.coordinate = coordinate;
        this.distance = distance;
        this.title = title;
    }

    public Coordinate coordinate;
    public double distance;
    private String title;

    public String toString() {
        return String.format("%s || %d", title, (int) Math.round(distance));
    }

    ;
}
