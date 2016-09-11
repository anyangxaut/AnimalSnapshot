package edu.xaut.model;

import java.util.LinkedHashMap;

/**
 * Created by anyang on 2016/9/10.
 */
public class Snapshot {
    private String snapshotId;
    private String snapshotTime;
    private LinkedHashMap<String, Animal> animals;

    public Snapshot(String snapshotId, String snapshotTime, LinkedHashMap<String, Animal> animals) {
        this.snapshotId = snapshotId;
        this.snapshotTime = snapshotTime;
        this.animals = animals;
    }

    public String getSnapshotId() {
        return snapshotId;
    }

    public String getSnapshotTime() {
        return snapshotTime;
    }

    public LinkedHashMap<String, Animal> getAnimals() {
        return animals;
    }
}
