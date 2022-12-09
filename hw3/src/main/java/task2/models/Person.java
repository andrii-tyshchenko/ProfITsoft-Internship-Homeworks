package task2.models;

import task2.annotations.Property;

import java.time.Instant;
import java.time.ZoneId;

/**
 * Representation of a person with some basic information about it.
 * <p>To be parsed from properties-file all fields must have setters.
 */
public class Person {
    @Property(name = "first_name")
    private String name;
    private String surname;
    private Integer id;
    @Property(name = "some.important.time", timeFormat = "dd.MM.yyyy HH:mm")
    private Instant someImportantTime;

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSomeImportantTime(Instant someImportantTime) {
        this.someImportantTime = someImportantTime;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", id=" + id +
                ", someImportantTime=" + (someImportantTime == null ? null : someImportantTime.atZone(ZoneId.systemDefault())) +
                '}';
    }
}