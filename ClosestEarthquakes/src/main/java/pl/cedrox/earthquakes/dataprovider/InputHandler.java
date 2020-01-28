package pl.cedrox.earthquakes.dataprovider;

import pl.cedrox.earthquakes.eqpicker.Coordinate;

import java.util.Locale;
import java.util.Scanner;

public class InputHandler {

    private double latitude = 0.0;
    private double longitude = 0.0;

    public void handleInput() {
        latitude = typeCoordinate("Type latitude");
        longitude = typeCoordinate("Type longitude");
    }

    private double typeCoordinate(String message) {
        Scanner keyboard = new Scanner(System.in).useLocale(Locale.US);
        System.out.println(message);

        while (!keyboard.hasNextDouble()) {
            String input = keyboard.next();
            System.out.printf("\"%s\" is not a valid number.\n", input);
        }
        return keyboard.nextDouble();
    }

    public Coordinate getInputData() {
        return new Coordinate(latitude, longitude);
    }

}
