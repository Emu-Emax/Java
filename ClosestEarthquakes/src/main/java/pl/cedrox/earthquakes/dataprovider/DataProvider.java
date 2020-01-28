package pl.cedrox.earthquakes.dataprovider;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pl.cedrox.earthquakes.eqpicker.Coordinate;
import pl.cedrox.earthquakes.eqpicker.EarthquakeRecord;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DataProvider {

    private JSONArray jsonData;
    private ArrayList<EarthquakeRecord> parsedRecords = new ArrayList<>();
    private static final String URL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_month.geojson";

    public void setJsonData(JSONArray jsonData) {
        this.jsonData = jsonData;
    }

    public void fetchData() throws IOException, ParseException {

        System.out.println("Fetching data... ");
        URL url = new URL(URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int responseCode = conn.getResponseCode();

        if (responseCode != 200)
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        else {
            parseData(url);
        }
    }

    private void parseData(URL url) throws IOException, ParseException {
        System.out.println("Parsing data... ");
        JSONParser parser = new JSONParser();
        String response = new String(url.openStream().readAllBytes());
        JSONObject jsonObject = (JSONObject) parser.parse(response);
        setJsonData((JSONArray) jsonObject.get("features"));
    }

    public void serializeData(Coordinate coordinate) {
        System.out.println("Serializing data... ");
        double jsonLongitude;
        double jsonLatitude;

        for (Object jsonRecord : this.jsonData) {
            JSONObject feature = (JSONObject) jsonRecord;
            JSONObject geometry = (JSONObject) feature.get("geometry");
            JSONObject properties = (JSONObject) feature.get("properties");
            JSONArray coordinates = (JSONArray) geometry.get("coordinates");
            try {
                jsonLongitude = (double) coordinates.get(0);
            } catch (ClassCastException e) {
                long l = (long) coordinates.get(0);
                jsonLongitude = (double) l;
            }

            try {
                jsonLatitude = (double) coordinates.get(1);
            } catch (ClassCastException e) {
                long l = (long) coordinates.get(1);
                jsonLatitude = (double) l;
            }

            Coordinate parsedCoordinate = new Coordinate(jsonLatitude, jsonLongitude);
            EarthquakeRecord newRecord = new EarthquakeRecord(parsedCoordinate, (int) Math.round(parsedCoordinate.countDistance(coordinate)),
                    (String) properties.get("title"));
            parsedRecords.add(newRecord);
        }
    }

    public ArrayList<EarthquakeRecord> getData() {
        return parsedRecords;
    }

}
