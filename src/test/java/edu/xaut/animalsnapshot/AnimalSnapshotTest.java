package edu.xaut.animalsnapshot;

import edu.xaut.exception.ConflictFoundException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by anyang on 2016/9/10.
 */
public class AnimalSnapshotTest {

    private AnimalSnapshot animalSnapshot;

    @Before
    public void setUp() throws Exception {
        animalSnapshot = new AnimalSnapshot();
    }

    @Test
    public void testGetSnapShotWithId1() throws Exception {
        final String historyData = "e4e87cb2-8e9a-4749-abb6-26c59344dfee\n" +
                "2016/09/02 22:30:46\n" +
                "cat1 10 9\n\n" +
                "351055db-33e6-4f9b-bfe1-16f1ac446ac1\n" +
                "2016/09/02 22:30:52\n" +
                "cat1 10 9 2 -1\n" +
                "cat2 2 3\n\n" +
                "dcfa0c7a-5855-4ed2-bc8c-4accae8bd155\n" +
                "2016/09/02 22:31:02\n" +
                "cat1 12 8 3 4";
        final String id = "e4e87cb2-8e9a-4749-abb6-26c59344dfee";

        String snapshotInSpecifiedTime = animalSnapshot.getSnapShot(historyData, id);

        assertEquals("cat1 10 9\n", snapshotInSpecifiedTime);
    }

    @Test
    public void testGetSnapShotWithId2() throws Exception {
        final String historyData = "e4e87cb2-8e9a-4749-abb6-26c59344dfee\n" +
                "2016/09/02 22:30:46\n" +
                "cat1 10 9\n\n" +
                "351055db-33e6-4f9b-bfe1-16f1ac446ac1\n" +
                "2016/09/02 22:30:52\n" +
                "cat1 10 9 2 -1\n" +
                "cat2 2 3\n\n" +
                "dcfa0c7a-5855-4ed2-bc8c-4accae8bd155\n" +
                "2016/09/02 22:31:02\n" +
                "cat1 12 8 3 4";
        final String id = "351055db-33e6-4f9b-bfe1-16f1ac446ac1";

        String snapshotInSpecifiedTime = animalSnapshot.getSnapShot(historyData, id);

        assertEquals("cat1 12 8\ncat2 2 3\n", snapshotInSpecifiedTime);
    }

    @Test
    public void testGetSnapShotWithId3() throws Exception {
        final String historyData = "e4e87cb2-8e9a-4749-abb6-26c59344dfee\n" +
                "2016/09/02 22:30:46\n" +
                "cat2 10 9\n\n" +
                "351055db-33e6-4f9b-bfe1-16f1ac446ac1\n" +
                "2016/09/02 22:30:52\n" +
                "cat2 10 9 2 -1\n" +
                "cat1 2 3\n\n" +
                "dcfa0c7a-5855-4ed2-bc8c-4accae8bd155\n" +
                "2016/09/02 22:31:02\n" +
                "cat2 12 8 3 4";
        final String id = "dcfa0c7a-5855-4ed2-bc8c-4accae8bd155";

        String snapshotInSpecifiedTime = animalSnapshot.getSnapShot(historyData, id);

        assertEquals("cat1 2 3\ncat2 15 12\n", snapshotInSpecifiedTime);
    }

    @Test
    public void testGetSnapShotWithInvalidFormatWhenSnapshotIdIsInvalid() throws Exception {
        final String historyData = "e4e87cb2hfghgfh-abb6-26c59344dfee\n" +
                "2016/09/02 22:30:46\n" +
                "cat1 10 9\n\n" +
                "351055db-33e6-4f9b-bfe1-16f1ac446ac1\n" +
                "2016/09/02 22:30:52\n" +
                "cat1 10 9 2 -1\n" +
                "cat2 2 3\n\n" +
                "dcfa0c7a-5855-4ed2-bc8c-4accae8bd155\n" +
                "2016/09/02 22:31:02\n" +
                "cat1 11 8 3 4";
        final String id = "dcfa0c7a-5855-4ed2-bc8c-4accae8bd155";

        String snapshotInSpecifiedTime = animalSnapshot.getSnapShot(historyData, id);

        assertEquals("Invalid format.", snapshotInSpecifiedTime);
    }

    @Test
    public void testGetSnapShotWithInvalidFormatWhenSnapshotTimeIsInvalid() throws Exception {
        final String historyData = "e4e87cb2-8e9a-4749-abb6-26c59344dfee\n" +
                "2016/0S/02 22:R0:46\n" +
                "cat1 10 9\n\n" +
                "351055db-33e6-4f9b-bfe1-16f1ac446ac1\n" +
                "2016/09/02 22:30:52\n" +
                "cat1 10 9 2 -1\n" +
                "cat2 2 3\n\n" +
                "dcfa0c7a-5855-4ed2-bc8c-4accae8bd155\n" +
                "2016/09/02 22:31:02\n" +
                "cat1 11 8 3 4";
        final String id = "dcfa0c7a-5855-4ed2-bc8c-4accae8bd155";

        String snapshotInSpecifiedTime = animalSnapshot.getSnapShot(historyData, id);

        assertEquals("Invalid format.", snapshotInSpecifiedTime);
    }

    @Test
    public void testGetSnapShotWithInvalidFormatWhenAnimalCoordinatesIsInvalid() throws Exception {
        final String historyData = "e4e87cb2-8e9a-4749-abb6-26c59344dfee\n" +
                "2016/09/02 22:30:46\n" +
                "cat1 10 9 wrewre\n\n" +
                "351055db-33e6-4f9b-bfe1-16f1ac446ac1\n" +
                "2016/09/02 22:30:52\n" +
                "cat1 10 9 2 -1\n" +
                "cat2 2 3\n\n" +
                "dcfa0c7a-5855-4ed2-bc8c-4accae8bd155\n" +
                "2016/09/02 22:31:02\n" +
                "cat1 11 8 3 4";
        final String id = "dcfa0c7a-5855-4ed2-bc8c-4accae8bd155";

        String snapshotInSpecifiedTime = animalSnapshot.getSnapShot(historyData, id);

        assertEquals("Invalid format.", snapshotInSpecifiedTime);
    }

    @Test
    public void testGetSnapShotWithDataConflict() throws Exception {
        final String historyData = "e4e87cb2-8e9a-4749-abb6-26c59344dfee\n" +
                "2016/09/02 22:30:46\n" +
                "cat1 10 9\n\n" +
                "351055db-33e6-4f9b-bfe1-16f1ac446ac1\n" +
                "2016/09/02 22:30:52\n" +
                "cat1 10 9 2 -1\n" +
                "cat2 2 3\n\n" +
                "dcfa0c7a-5855-4ed2-bc8c-4accae8bd155\n" +
                "2016/09/02 22:31:02\n" +
                "cat1 11 8 3 4";
        final String id = "dcfa0c7a-5855-4ed2-bc8c-4accae8bd155";

        String snapshotInSpecifiedTime = animalSnapshot.getSnapShot(historyData, id);

        assertEquals("Conflict found at dcfa0c7a-5855-4ed2-bc8c-4accae8bd155", snapshotInSpecifiedTime);
    }
}