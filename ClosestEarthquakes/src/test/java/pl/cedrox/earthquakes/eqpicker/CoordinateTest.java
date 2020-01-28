package pl.cedrox.earthquakes.eqpicker;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotSame;

public class CoordinateTest {

    private Coordinate coordinate;

    @Before
    public void setUp() {
        coordinate = new Coordinate(20, 40);
    }

    @Test
    public void testCountDistance() {
        Coordinate otherCoordinate = new Coordinate(30, 50);
        double distance = coordinate.countDistance(otherCoordinate);
        assertEquals(1499, (int) distance);
    }

    @Test
    public void testCountDistanceNegativeNumbers() {
        Coordinate negativeNumbersCoordinate = new Coordinate(-10, -70);
        Coordinate otherCoordinate = new Coordinate(-50, -30);
        double distance = negativeNumbersCoordinate.countDistance(otherCoordinate);
        assertEquals(5763, (int) distance);
    }

    @Test
    public void testCountDistanceZeroNumbers() {
        Coordinate zeroNumbersCoordinate = new Coordinate(0, 0);
        Coordinate otherCoordinate = new Coordinate(0, 0);
        double distance = zeroNumbersCoordinate.countDistance(otherCoordinate);
        assertEquals(0.0, distance);
    }

    @Test
    public void testEquals() {
        Coordinate otherCoordinate = new Coordinate(20, 40);
        assertEquals(coordinate, otherCoordinate);
    }

    @Test
    public void testNotEquals() {
        Coordinate otherCoordinate = new Coordinate(30, 70);
        assertNotSame(coordinate, otherCoordinate);
    }

}