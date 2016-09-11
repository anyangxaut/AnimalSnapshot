package edu.xaut.model;

/**
 * Created by anyang on 2016/9/10.
 */
public class Animal {
    private String animalId;
    private int xCoordinate;
    private int yCoordinate;
    private int xOffset;
    private int yOffset;

    public Animal(String animalId, int xCoordinate, int yCoordinate) {
        this.animalId = animalId;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.xOffset = 0;
        this.yOffset = 0;
    }

    public Animal(String animalId, int xCoordinate, int yCoordinate, int xOffset, int yOffset) {
        this.animalId = animalId;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public String getAnimalId() {
        return animalId;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public int getxCoordinateInCurrentTime() {
        return xCoordinate + xOffset;
    }

    public int getyCoordinateInCurrentTime() {
        return yCoordinate + yOffset;
    }
}
