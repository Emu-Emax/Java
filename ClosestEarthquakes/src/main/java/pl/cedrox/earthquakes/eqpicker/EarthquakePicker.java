package pl.cedrox.earthquakes.eqpicker;

import java.util.ArrayList;
import java.util.Comparator;


public class EarthquakePicker {

    private ArrayList<EarthquakeRecord> data;
    private ArrayList<EarthquakeRecord> results = new ArrayList<>();

    public EarthquakePicker(ArrayList<EarthquakeRecord> data) {
        this.data = data;
    }

    /**
     * Puts records with minimal distance from initial coordinate in results list
     */
    public void pickNearestEarthQuakes(int max_number_of_records) {
        data.sort(Comparator.comparingDouble((EarthquakeRecord o) -> o.distance));
        try {
            while (results.size() != max_number_of_records) {
                if (!isDuplicatedRecord(data.get(0))) results.add(data.remove(0));
                else
                    data.remove(0);
            }
        } catch (IndexOutOfBoundsException e) {
            return;
        }
    }

    /**
     * Checks if given latitude and longitude already exist in results
     */
    private boolean isDuplicatedRecord(EarthquakeRecord checkedRecord) {
        for (EarthquakeRecord savedResult : results) {
            if (checkedRecord.coordinate.equals(savedResult.coordinate))
                return true;
        }
        return false;
    }

    public void printCalculations() {
        System.out.println("Printing nearest main.java.pl.earthquakes.earthquakes... ");
        for (EarthquakeRecord record : results) System.out.println(record.toString());
    }

    public ArrayList<EarthquakeRecord> getResults() {
        return this.results;
    }
}