package pl.cedrox.earthquakes;

import org.json.simple.parser.ParseException;
import pl.cedrox.earthquakes.dataprovider.DataProvider;
import pl.cedrox.earthquakes.dataprovider.InputHandler;
import pl.cedrox.earthquakes.eqpicker.Coordinate;
import pl.cedrox.earthquakes.eqpicker.EarthquakePicker;

import java.io.IOException;


public class App {


    public static void main(String[] args) throws IOException, ParseException {

        InputHandler inputHandler = new InputHandler();
        inputHandler.handleInput();
        Coordinate inputData = inputHandler.getInputData();

        DataProvider dataProvider = new DataProvider();
        dataProvider.fetchData();
        dataProvider.serializeData(inputData);

        int MAX_NUMBER_OF_RESULTS = 10;
        EarthquakePicker earthquakePicker = new EarthquakePicker(dataProvider.getData());
        earthquakePicker.pickNearestEarthQuakes(MAX_NUMBER_OF_RESULTS);
        earthquakePicker.printCalculations();

    }

}
