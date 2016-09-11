package edu.xaut.animalsnapshot;

import edu.xaut.model.Animal;
import edu.xaut.model.Snapshot;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by anyang on 2016/9/10.
 */
public class DataFormatWithOriginalCoordinates {
    private final String data;

    public DataFormatWithOriginalCoordinates(String data) {
        this.data = data;
    }

    public LinkedHashMap<String, Snapshot> format() {
        LinkedHashMap<String, Snapshot> historySnapshot = new LinkedHashMap<String, Snapshot>();
        String[] dataArray = data.split("\n");

        for (int i = 0; i < dataArray.length; i++) {
            String id = dataArray[i++];
            String time = dataArray[i++];
            List<Animal> animals = new ArrayList<Animal>();
            do {
                animals.add(createAnimal(dataArray[i++]));
            }while((i < dataArray.length) && (!dataArray[i].equals("")));
            Snapshot snapshot = createSnapshot(id, time, animals);
            historySnapshot.put(id, snapshot);
        }
        System.out.println(historySnapshot);
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

    private Snapshot createSnapshot(String id, String time, List<Animal> animals) {
        Snapshot snapshot = new Snapshot(id, time, animals);
        return snapshot;
    }
}
