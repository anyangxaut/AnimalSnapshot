package edu.xaut.animalsnapshot;

import edu.xaut.model.Animal;
import edu.xaut.model.Snapshot;

import java.util.*;

/**
 * Created by anyang on 2016/9/10.
 */
public class SnapshotPrinter {

    public String printMultipleAnimalCoordinates(Snapshot snapshot) {
        List<Animal> animals = sortWithAnimalId(snapshot.getAnimals());
        StringBuilder AnimalCoordinates = new StringBuilder();
        for (Animal animal : animals) {
            AnimalCoordinates.append(printEachAnimalCoordinates(animal));
        }
        return AnimalCoordinates.toString();
    }

    public List<Animal> sortWithAnimalId(LinkedHashMap<String, Animal> animals) {
        List<Animal> animalsList = new ArrayList<Animal>();
        for (String animalId : animals.keySet()) {
            animalsList.add(animals.get(animalId));
        }
        Collections.sort(animalsList, new Comparator<Animal>() {
            public int compare(Animal o1, Animal o2) {
                return o1.getAnimalId().compareTo(o2.getAnimalId());
            }
        });
        return animalsList;
    }

    public String printEachAnimalCoordinates(Animal animal) {
        return String.format("%s %d %d\n", animal.getAnimalId(), animal.getxCoordinateInCurrentTime(),
                animal.getyCoordinateInCurrentTime());
    }
}
