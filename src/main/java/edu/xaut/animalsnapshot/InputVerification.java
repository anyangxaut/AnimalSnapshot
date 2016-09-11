package edu.xaut.animalsnapshot;

import edu.xaut.exception.ConflictFoundException;
import edu.xaut.exception.InvalidFormatException;
import edu.xaut.model.Animal;

/**
 * Created by anyang on 2016/9/10.
 */
public class InputVerification {
    private String idValidRegex = "^[A-Za-z0-9]{8}-[A-Za-z0-9]{4}-[A-Za-z0-9]{4}-[A-Za-z0-9]{4}-[A-Za-z0-9]{12}$";
    private String timeValidRegex = "^\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}$";

    public String isSnapshotIdValid(String snapshotId) throws InvalidFormatException {
        if (!snapshotId.matches(idValidRegex)) {
            throw new InvalidFormatException("Invalid format for SnapshotId.");
        }
        return snapshotId;
    }

    public String isSnapshotTimeValid(String snapshotTime) throws InvalidFormatException {
        if (!snapshotTime.matches(timeValidRegex)) {
            throw new InvalidFormatException("Invalid format for SnapshotTime.");
        }
        return snapshotTime;
    }

    public String isAnimalCoordinatesValid(String animalCoordinates) throws InvalidFormatException {
        String[] informations = animalCoordinates.split(" ");
        if (informations.length != 3 && informations.length != 5) {
            throw new InvalidFormatException("Invalid format for AnimalCoordinates.");
        }
        return animalCoordinates;
    }

    public boolean isCoordinatesConflict(Animal lastInputAnimal, Animal currentInputAnimal) throws ConflictFoundException {
        boolean xCoordinateConflict = isExsitConflictData(lastInputAnimal.getxCoordinateInCurrentTime(),
                currentInputAnimal.getxCoordinate());
        boolean yCoordinateConflict = isExsitConflictData(lastInputAnimal.getyCoordinateInCurrentTime(),
                currentInputAnimal.getyCoordinate());
        if (xCoordinateConflict || yCoordinateConflict){
            return true;
        }
        return false;
    }

    private boolean isExsitConflictData(int expected, int actual) {
        if (expected == actual) {
            return false;
        }else {
            return true;
        }
    }
}
