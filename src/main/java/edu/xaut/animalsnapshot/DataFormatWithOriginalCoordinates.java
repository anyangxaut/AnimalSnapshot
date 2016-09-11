package edu.xaut.animalsnapshot;

import edu.xaut.exception.InvalidFormatException;
import edu.xaut.model.Animal;
import edu.xaut.model.Snapshot;

import java.util.LinkedHashMap;

/**
 * Created by anyang on 2016/9/10.
 */
public class DataFormatWithOriginalCoordinates {
    private InputVerification inputVerification;

    public DataFormatWithOriginalCoordinates() {
        this.inputVerification = new InputVerification();
    }

    public LinkedHashMap<String, Snapshot> format(String data) throws InvalidFormatException {
        LinkedHashMap<String, Snapshot> historySnapshot = new LinkedHashMap<String, Snapshot>();
        String[] dataArray = data.split("\n");
        for (int i = 0; i < dataArray.length; i++) {
            String snapshotId = inputVerification.isSnapshotIdValid(dataArray[i++]);
            String snapshotTime = inputVerification.isSnapshotTimeValid(dataArray[i++]);
            LinkedHashMap<String, Animal> animals = new LinkedHashMap<String, Animal>();
            do {
                String animalCoordinates = inputVerification.isAnimalCoordinatesValid(dataArray[i++]);
                Animal animal = createAnimal(animalCoordinates);
                animals.put(animal.getAnimalId(), animal);
            }while((i < dataArray.length) && (!dataArray[i].equals("")));
            Snapshot snapshot = createSnapshot(snapshotId, snapshotTime, animals);
            historySnapshot.put(snapshotId, snapshot);
        }
        return historySnapshot;
    }

    private Animal createAnimal(String animalInfo) {
        String[] informations = animalInfo.split(" ");
        Animal animal;
        if (informations.length == 3) {
            animal = new Animal(informations[0], Integer.parseInt(informations[1]),
                    Integer.parseInt(informations[2]));
        }else {
            animal = new Animal(informations[0], Integer.parseInt(informations[1]),
                    Integer.parseInt(informations[2]), Integer.parseInt(informations[3]),
                    Integer.parseInt(informations[4]));
        }
        return animal;
    }

    private Snapshot createSnapshot(String id, String time, LinkedHashMap<String, Animal> animals) {
        Snapshot snapshot = new Snapshot(id, time, animals);
        return snapshot;
    }
}
