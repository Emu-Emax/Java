package pl.cedrox.earthquakes.eqpicker;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import pl.cedrox.earthquakes.dataprovider.DataProvider;

import java.io.FileReader;
import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class EarthquakePickerTest {

    EarthquakePicker eqPicker;
    DataProvider dataProvider;
    Coordinate coordinate;
    JSONArray jsonData;

    /**
     * JSON includes duplicate coordination case
     */
    private void parseJsonFromFile() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("src/test/resources/jsonData.json")) {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            this.jsonData = (JSONArray) jsonObject.get("features");

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setUp() {
        parseJsonFromFile();
        dataProvider = new DataProvider();
        dataProvider.setJsonData(this.jsonData);
        coordinate = new Coordinate(10, 20);
        dataProvider.serializeData(coordinate);
        eqPicker = new EarthquakePicker(dataProvider.getData());
    }


    @Test
    public void testPickNearestEarthQuakes() {
        eqPicker.pickNearestEarthQuakes(10);
        int[] expectedDistances = new int[]{1112, 1569, 1569, 2476, 3500, 4562, 5160, 5233, 5624, 5641};

        for (int i = 0; i < 10; i++)
            assertEquals(expectedDistances[i], (int) eqPicker.getResults().get(i).distance);
    }

    @Test
    public void testPickNearestEarthQuakesTooManyMaxRecords() {
        eqPicker.pickNearestEarthQuakes(300);
        int[] expectedDistances = new int[]{1112, 1569, 1569, 2476, 3500, 4562, 5160, 5233, 5624, 5641};

        for (int i = 0; i < 10; i++)
            assertEquals(expectedDistances[i], (int) eqPicker.getResults().get(i).distance);
    }

}