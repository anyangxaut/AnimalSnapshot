package edu.xaut.animalsnapshot;

import edu.xaut.model.Animal;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by anyang on 2016/9/10.
 */
public class InputVerificationTest {

    private InputVerification inputVerification;

    @Before
    public void setUp() throws Exception {
        inputVerification = new InputVerification();
    }

    @Test
    public void testIsSnapshotIdValidWhenIdIsValid() throws Exception {
        String expectedId = "e4e87cb2-8e9a-4749-abb6-26c59344dfee";

        String actualId = inputVerification.isSnapshotIdValid(expectedId);

        assertEquals(expectedId, actualId);
    }

    @Test (expected = edu.xaut.exception.InvalidFormatException.class)
    public void testIsSnapshotIdValidWhenIdIsNotValidWithBlank() throws Exception {
        String expectedId = "e4e87cb2-8e   9a-4749-abb6-26c59344dfee";

        String actualId = inputVerification.isSnapshotIdValid(expectedId);

        assertEquals(expectedId, actualId);
    }

    @Test (expected = edu.xaut.exception.InvalidFormatException.class)
    public void testIsSnapshotIdValidWhenIdIsNotValidWithEmpty() throws Exception {
        String expectedId = "";

        String actualId = inputVerification.isSnapshotIdValid(expectedId);

        assertEquals(expectedId, actualId);
    }

    @Test
    public void testIsSnapshotTimeValidWhenTimeIsValid() throws Exception {
        String expectedTime = "2016/09/02 22:30:46";

        String actualTime = inputVerification.isSnapshotTimeValid(expectedTime);

        assertEquals(expectedTime, actualTime);
    }

    @Test (expected = edu.xaut.exception.InvalidFormatException.class)
    public void testIsSnapshotTimeValidWhenTimeIsNotValidWithIllegalCharacter() throws Exception {
        String expectedTime = "201P/09/S2 22:30:46";

        String actualTime = inputVerification.isSnapshotIdValid(expectedTime);

        assertEquals(expectedTime, actualTime);
    }

    @Test (expected = edu.xaut.exception.InvalidFormatException.class)
    public void testIsSnapshotTimeValidWhenTimeIsNotValidWithEmpty() throws Exception {
        String expectedTime = "";

        String actualTime = inputVerification.isSnapshotIdValid(expectedTime);

        assertEquals(expectedTime, actualTime);
    }

    @Test
    public void testIsAnimalCoordinatesValidWhenCoordinatesIsValidAndLengthIsThree() throws Exception {
        String expectedCoordinates = "cat1 10 9";

        String actualCoordinates = inputVerification.isAnimalCoordinatesValid(expectedCoordinates);

        assertEquals(expectedCoordinates, actualCoordinates);
    }

    @Test
    public void testIsAnimalCoordinatesValidWhenCoordinatesIsValidAndLengthIsFive() throws Exception {
        String expectedCoordinates = "cat1 12 8 3 4";

        String actualCoordinates = inputVerification.isAnimalCoordinatesValid(expectedCoordinates);

        assertEquals(expectedCoordinates, actualCoordinates);
    }

    @Test (expected = edu.xaut.exception.InvalidFormatException.class)
    public void testIsAnimalCoordinatesValidWhenCoordinatesIsNotValid() throws Exception {
        String expectedCoordinates = "cat1 12 8 3fgfhgh";

        String actualCoordinates = inputVerification.isAnimalCoordinatesValid(expectedCoordinates);

        assertEquals(expectedCoordinates, actualCoordinates);
    }

    @Test
    public void testIsCoordinatesConflictWhenCoordinatesAreEqual() throws Exception {
        Animal lastInputAnimal = mock(Animal.class);
        when(lastInputAnimal.getxCoordinateInCurrentTime()).thenReturn(2);
        when(lastInputAnimal.getyCoordinateInCurrentTime()).thenReturn(5);
        Animal currentInputAnimal = mock(Animal.class);
        when(currentInputAnimal.getxCoordinate()).thenReturn(2);
        when(currentInputAnimal.getyCoordinate()).thenReturn(5);

        boolean isConflict = inputVerification.isCoordinatesConflict(lastInputAnimal, currentInputAnimal);

        assertEquals(false, isConflict);
    }

    @Test
    public void testIsCoordinatesConflictWhenXCoordinateNotEqual() throws Exception {
        Animal lastInputAnimal = mock(Animal.class);
        when(lastInputAnimal.getxCoordinateInCurrentTime()).thenReturn(2);
        when(lastInputAnimal.getyCoordinateInCurrentTime()).thenReturn(5);
        Animal currentInputAnimal = mock(Animal.class);
        when(currentInputAnimal.getxCoordinate()).thenReturn(8);
        when(currentInputAnimal.getyCoordinate()).thenReturn(5);

        boolean isConflict = inputVerification.isCoordinatesConflict(lastInputAnimal, currentInputAnimal);

        assertEquals(true, isConflict);
    }

    @Test
    public void testIsCoordinatesConflictWhenYCoordinateNotEqual() throws Exception {
        Animal lastInputAnimal = mock(Animal.class);
        when(lastInputAnimal.getxCoordinateInCurrentTime()).thenReturn(2);
        when(lastInputAnimal.getyCoordinateInCurrentTime()).thenReturn(5);
        Animal currentInputAnimal = mock(Animal.class);
        when(currentInputAnimal.getxCoordinate()).thenReturn(2);
        when(currentInputAnimal.getyCoordinate()).thenReturn(9);

        boolean isConflict = inputVerification.isCoordinatesConflict(lastInputAnimal, currentInputAnimal);

        assertEquals(true, isConflict);
    }

    @Test
    public void testIsCoordinatesConflictWhenCoordinatesNotEqual() throws Exception {
        Animal lastInputAnimal = mock(Animal.class);
        when(lastInputAnimal.getxCoordinateInCurrentTime()).thenReturn(2);
        when(lastInputAnimal.getyCoordinateInCurrentTime()).thenReturn(5);
        Animal currentInputAnimal = mock(Animal.class);
        when(currentInputAnimal.getxCoordinate()).thenReturn(3);
        when(currentInputAnimal.getyCoordinate()).thenReturn(7);

        boolean isConflict = inputVerification.isCoordinatesConflict(lastInputAnimal, currentInputAnimal);

        assertEquals(true, isConflict);
    }
}