package pl.cedrox.earthquakes.dataprovider;


import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import pl.cedrox.earthquakes.eqpicker.Coordinate;
import pl.cedrox.earthquakes.eqpicker.EarthquakeRecord;

import java.io.IOException;

import static org.junit.Assert.assertNotEquals;


public class DataProviderTest {

    DataProvider dataProvider;
    Coordinate coordinate;


    @Before
    public void setUp() throws IOException, ParseException {
        dataProvider = new DataProvider();
        dataProvider.fetchData();
        coordinate = new Coordinate(10, 20);
    }

    @Test
    public void testSerializeData() {
        dataProvider.serializeData(coordinate);
        for (EarthquakeRecord record : dataProvider.getData()) {
            assertNotEquals(record.distance, null);
        }
    }

}