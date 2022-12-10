package task2.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import task2.exceptions.PropertyNotFoundException;
import task2.models.Person;
import task2.services.testModels.PersonWithoutDefaultConstructor;
import task2.services.testModels.PersonWithoutSetterMethod;

import java.io.File;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static task2.services.ObjectFromPropertiesUtil.*;

class ObjectFromPropertiesUtilTest {
    private final File personPropertiesFile = new File("src/test/resources/properties/person.properties");

    @Test
    @DisplayName("Standard test")
    void loadFromPropertiesIfAllIsOk() {
        try {
            Person person = loadFromProperties(Person.class, personPropertiesFile);

            assertEquals("John", person.getName());
            assertEquals("Doe", person.getSurname());
            assertEquals(245, person.getId());
            assertEquals("01.10.2019 10:25",
                        DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
                                .withZone(ZoneId.systemDefault())
                                .format(person.getSomeImportantTime()));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Empty file")
    void loadFromEmptyProperties() {
        File emptyPersonPropertiesFile = new File("src/test/resources/properties/empty_person.properties");

        assertThrows(PropertyNotFoundException.class, () -> loadFromProperties(Person.class, emptyPersonPropertiesFile));
    }

    @Test
    @DisplayName("No default constructor")
    void loadFromPropertiesIfNoDefaultConstructor() {
        PersonWithoutDefaultConstructor person = new PersonWithoutDefaultConstructor("");

        Exception actualException = assertThrows(NoSuchMethodException.class,
                () -> loadFromProperties(PersonWithoutDefaultConstructor.class, personPropertiesFile));

        assertEquals(person.getClass() + " doesn't have default (empty) constructor", actualException.getMessage());
    }

    @Test
    @DisplayName("No setter method")
    void loadFromPropertiesIfNoSetter() throws NoSuchFieldException {
        Exception actualException = assertThrows(NoSuchMethodException.class,
                () -> loadFromProperties(PersonWithoutSetterMethod.class, personPropertiesFile));

        assertEquals("Field " + PersonWithoutSetterMethod.class.getDeclaredField("name") + " has no setter method",
                actualException.getMessage());
    }

    @Test
    @DisplayName("Property not found")
    void loadFromPropertiesIfNoProperty() throws NoSuchFieldException {
        File carPropertiesFile = new File("src/test/resources/properties/car.properties");

        Exception actualException = assertThrows(PropertyNotFoundException.class,
                () -> loadFromProperties(Person.class, carPropertiesFile));

        assertEquals("Property for field " + Person.class.getDeclaredField("name") + " not found",
                actualException.getMessage());
    }

    @Test
    @DisplayName("Can't parse integer")
    void loadFromPropertiesIfProblemsWithInteger() {
        File personWithWrongIdPropertiesFile = new File("src/test/resources/properties/person_wrong_id.properties");

        Exception actualException = assertThrows(NumberFormatException.class,
                () -> loadFromProperties(Person.class, personWithWrongIdPropertiesFile));

        assertEquals("Can't parse integer from string 'q245'", actualException.getMessage());
    }
}