package edu.xaut.animalsnapshot;

import edu.xaut.model.Animal;
import edu.xaut.model.Snapshot;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by anyang on 2016/9/10.
 */
public class SnapshotPrinter {

    public String printMultipleAnimalCoordinates(Snapshot snapshot) {
        LinkedHashMap<String, Animal> animals = snapshot.getAnimals();
        StringBuilder AnimalCoordinates = new StringBuilder();
        for (String animalId : animals.keySet()) {
            AnimalCoordinates.append(printEachAnimalCoordinates(animals.get(animalId)));
        }
        return AnimalCoordinates.toString();
    }

    public String printEachAnimalCoordinates(Animal animal) {
        return String.format("%s %d %d\n", animal.getAnimalId(), animal.getxCoordinateInCurrentTime(),
                animal.getyCoordinateInCurrentTime());
    }
}
