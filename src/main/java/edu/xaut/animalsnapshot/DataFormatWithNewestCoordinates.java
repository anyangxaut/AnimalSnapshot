package edu.xaut.animalsnapshot;

import edu.xaut.exception.ConflictFoundException;
import edu.xaut.model.Animal;
import edu.xaut.model.Snapshot;

import java.util.LinkedHashMap;

/**
 * Created by anyang on 2016/9/10.
 */
public class DataFormatWithNewestCoordinates {

    private InputVerification inputVerification;

    public DataFormatWithNewestCoordinates() {
        this.inputVerification = new InputVerification();
    }

    public LinkedHashMap<String, Snapshot> format(LinkedHashMap<String, Snapshot> originalCoordinates)
            throws ConflictFoundException {
        LinkedHashMap<String, Snapshot> newestCoordinates =
                calculateNewestCoordinatesForAllAnimalsInAllTime(originalCoordinates);
        return newestCoordinates;
    }

    private LinkedHashMap<String, Snapshot> calculateNewestCoordinatesForAllAnimalsInAllTime(
            LinkedHashMap<String, Snapshot> originalCoordinates) throws ConflictFoundException {
        LinkedHashMap<String, Snapshot> newestCoordinates = new LinkedHashMap<String, Snapshot>();
        LinkedHashMap<String, Animal> lastAnimals = new LinkedHashMap<String, Animal>();
        for (String snapshotId : originalCoordinates.keySet()) {
            Snapshot originalSnapshot = originalCoordinates.get(snapshotId);
            verifyMultipleData(originalSnapshot, lastAnimals);
            LinkedHashMap<String, Animal> newestAnimals = calculateCurrentCoordinates(originalSnapshot, lastAnimals);
            Snapshot newestSnapshot = new Snapshot(originalSnapshot.getSnapshotId(),
                    originalSnapshot.getSnapshotTime(), newestAnimals);
            newestCoordinates.put(snapshotId, newestSnapshot);
            lastAnimals = newestAnimals;
        }
        return newestCoordinates;
    }

    private void verifyMultipleData(Snapshot originalSnapshot,
                                    LinkedHashMap<String, Animal> lastAnimals) throws ConflictFoundException {
        LinkedHashMap<String, Animal> originalAnimals = originalSnapshot.getAnimals();
        for (String animalId : lastAnimals.keySet()) {
            if(originalAnimals.containsKey(animalId)){
                if (inputVerification.isCoordinatesConflict(lastAnimals.get(animalId), originalAnimals.get(animalId))) {
                    throw new ConflictFoundException("Conflict found at " + originalSnapshot.getSnapshotId());
                }
            }
        }
    }

    private LinkedHashMap<String, Animal> calculateCurrentCoordinates(
            Snapshot originalSnapshot, LinkedHashMap<String, Animal> lastAnimals)
            throws ConflictFoundException {
        LinkedHashMap<String, Animal> originalAnimals = originalSnapshot.getAnimals();
        LinkedHashMap<String, Animal> newestAnimals = new LinkedHashMap<String, Animal>();
        for (String animalId : originalAnimals.keySet()) {
            Animal originalAnimal = originalAnimals.get(animalId);
            Animal newestAnimal = createAnimalWithCurrentCoordinates(originalAnimal);
            newestAnimals.put(newestAnimal.getAnimalId(), newestAnimal);
        }
        return addCoordinatesForBeforeAppearedAnimals(lastAnimals, newestAnimals);
    }

    private Animal createAnimalWithCurrentCoordinates(Animal originalAnimal) {
        int xCoordinateInCurrentTime = originalAnimal.getxCoordinateInCurrentTime();
        int yCoordinateInCurrentTime = originalAnimal.getyCoordinateInCurrentTime();
        Animal newestAnimal = new Animal(originalAnimal.getAnimalId(), xCoordinateInCurrentTime,
                    yCoordinateInCurrentTime);
        return newestAnimal;
    }

    private LinkedHashMap<String, Animal> addCoordinatesForBeforeAppearedAnimals(
            LinkedHashMap<String, Animal> lastAnimals, LinkedHashMap<String, Animal> newestAnimals) {
        for (String animalId : lastAnimals.keySet()) {
            if (!newestAnimals.containsKey(animalId)) {
                newestAnimals.put(animalId, lastAnimals.get(animalId));
            }
        }
        return newestAnimals;
    }
}

