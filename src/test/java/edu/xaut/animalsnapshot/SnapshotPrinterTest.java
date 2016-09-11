package edu.xaut.animalsnapshot;

import edu.xaut.model.Animal;
import edu.xaut.model.Snapshot;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by anyang on 2016/9/11.
 */
public class SnapshotPrinterTest {

    private SnapshotPrinter snapshotPrinter;

    @Before
    public void setUp() throws Exception {
        snapshotPrinter = new SnapshotPrinter();
    }

    @Test
    public void testPrintMultipleAnimalCoordinates() throws Exception {
        LinkedHashMap<String, Animal> animals = new LinkedHashMap<String, Animal>();
        animals.put("cat1", new Animal("cat1", 10, 9, 2, -1));
        animals.put("cat2", new Animal("cat2", 2, 3));
        Snapshot snapshot = mock(Snapshot.class);
        when(snapshot.getAnimals()).thenReturn(animals);

        String actual = snapshotPrinter.printMultipleAnimalCoordinates(snapshot);

        assertEquals("cat1 12 8\ncat2 2 3\n", actual);
    }

    @Test
    public void testPrintEachAnimalCoordinates() throws Exception {
        Animal animal = mock(Animal.class);
        when(animal.getAnimalId()).thenReturn("cat1");
        when(animal.getxCoordinateInCurrentTime()).thenReturn(12);
        when(animal.getyCoordinateInCurrentTime()).thenReturn(8);

        String actual = snapshotPrinter.printEachAnimalCoordinates(animal);

        assertEquals("cat1 12 8\n", actual);
    }

}