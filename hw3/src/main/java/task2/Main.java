package task2;

import task2.models.Car;
import task2.models.Person;
import task2.services.ObjectFromPropertiesUtil;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File personPropertiesFile = new File("hw3/src/main/resources/properties/person.properties");
        File carPropertiesFile = new File("hw3/src/main/resources/properties/car.properties");

        try {
            Person person = ObjectFromPropertiesUtil.loadFromProperties(Person.class, personPropertiesFile);
            System.out.println(person);

            Car car = ObjectFromPropertiesUtil.loadFromProperties(Car.class, carPropertiesFile);
            System.out.println(car);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}