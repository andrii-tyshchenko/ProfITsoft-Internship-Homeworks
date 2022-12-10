package task2.services.testModels;

import task2.annotations.Property;

public class PersonWithoutSetterMethod {
    @Property(name = "first_name")
    private String name;
}