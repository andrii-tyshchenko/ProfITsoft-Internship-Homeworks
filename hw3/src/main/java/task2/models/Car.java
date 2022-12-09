package task2.models;

import task2.annotations.Property;

import java.time.Instant;
import java.time.ZoneId;

/**
 * Representation of a car with some basic information about it.
 * <p>To be parsed from properties-file all fields must have setters.
 */
public class Car {
    private String brand;
    private String model;
    private int doors;
    @Property(timeFormat = "yyyy/MM/dd HH:mm")
    private Instant timeOfBuying;

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public void setTimeOfBuying(Instant timeOfBuying) {
        this.timeOfBuying = timeOfBuying;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", doors=" + doors +
                ", timeOfBuying=" + (timeOfBuying == null ? null : timeOfBuying.atZone(ZoneId.systemDefault())) +
                '}';
    }
}