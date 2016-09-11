package edu.xaut.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by anyang on 2016/9/11.
 */
public class AnimalTest {

    private Animal animal;

    @Before
    public void setUp() throws Exception {
        animal = new Animal("cat1", 10, 8, 2, -1);
    }

    @Test
    public void testGetxCoordinateInCurrentTime() throws Exception {
        int expectedCoordinate = 12;

        int actualCoordinate = animal.getxCoordinateInCurrentTime();

        assertEquals(expectedCoordinate, actualCoordinate);
    }

    @Test
    public void testGetyCoordinateInCurrentTime() throws Exception {
        int expectedCoordinate = 7;

        int actualCoordinate = animal.getyCoordinateInCurrentTime();

        assertEquals(expectedCoordinate, actualCoordinate);
    }
}