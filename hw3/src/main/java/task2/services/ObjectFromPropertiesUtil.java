package task2.services;

import org.apache.commons.beanutils.PropertyUtils;
import task2.annotations.Property;
import task2.exceptions.PropertyNotFoundException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class ObjectFromPropertiesUtil {
    /**
     * Creates an object of given class and fills its fields with values, parsed from given properties-file.
     * <p>Uses Apache Commons BeanUtils library for setting values to the fields,
     * so all fields of given class must have setters.
     * @param clazz class of object which will be created
     * @param propertiesFile file with required properties
     * @return object of given class, which fields' values parsed from given properties-file
     * @throws IOException if there are some issues with reading properties-file
     * @throws PropertyNotFoundException if properties-file is empty or needed property is absent
     * @throws NoSuchMethodException if given class doesn't have default constructor or its field doesn't have setter method
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T> T loadFromProperties(Class<T> clazz, File propertiesFile)
            throws IOException, PropertyNotFoundException, NoSuchMethodException,
                    InvocationTargetException, InstantiationException, IllegalAccessException {
        Properties properties = new Properties();

        try(FileInputStream fis = new FileInputStream(propertiesFile)) {
            properties.load(fis);

            if (properties.isEmpty()) {
                throw new PropertyNotFoundException("File " + propertiesFile + " is empty or doesn't contain properties");
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File " + propertiesFile.getPath() + " not found");
        }

        T object = null;

        try {
            object = clazz.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodException(clazz + " doesn't have default (empty) constructor");
        }

        Field[] declaredFields = clazz.getDeclaredFields();

        for (Field field : declaredFields) {
            try {
                PropertyUtils.setProperty(object, field.getName(), getValueForFieldFromProperties(field, properties));
            } catch (NoSuchMethodException e) {
                throw new NoSuchMethodException("Field " + field + " has no setter method");
            }
            //version without Apache Commons BeanUtils library:
            /*
            if (!field.canAccess(object)) {
                field.setAccessible(true);

                field.set(object, getValueForFieldFromProperties(field, properties));

                field.setAccessible(false);
            } else {
                field.set(object, getValueForFieldFromProperties(field, properties));
            }
            */
        }

        return object;
    }

    /**
     * Returns value for given field, taken from given {@code Properties} object
     * @param field field to which you want to assign a value
     * @param properties properties, loaded from properties-file
     * @return value for given field
     * @throws PropertyNotFoundException if needed property is absent
     */
    private static Object getValueForFieldFromProperties(Field field, Properties properties) throws PropertyNotFoundException {
        String key = field.getName();

        if (field.isAnnotationPresent(Property.class)) {
            String propertyName = field.getAnnotation(Property.class).name().trim();

            if (!propertyName.isEmpty()) {
                key = propertyName;
            }
        }

        String propertyValue = properties.getProperty(key);

        if (propertyValue == null) {
            throw new PropertyNotFoundException("Property for field " + field + " not found");
        }

        return parseFieldValueFromProperty(field, propertyValue);
    }

    /**
     * Returns value for given field parsed from given property.
     * @param field field for which you want to parse a value
     * @param propertyValue corresponding property which you want to parse
     * @return parsed value of property for given field
     */
    private static Object parseFieldValueFromProperty(Field field, String propertyValue) {
        Object valueForField = null;

        Class<?> fieldClass = field.getType();

        //STRING
        if (fieldClass.isAssignableFrom(String.class)) {
            valueForField = propertyValue;
        }

        //INTEGER, INT
        if (fieldClass.isAssignableFrom(Integer.class) || fieldClass.isAssignableFrom(int.class)) {
            try {
                valueForField = Integer.parseInt(propertyValue);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Can't parse integer from string '" + propertyValue + "'");
            }
        }

        //INSTANT
        if (fieldClass.isAssignableFrom(Instant.class)) {
            String timePattern = "";

            if (field.isAnnotationPresent(Property.class)) {
                timePattern = field.getAnnotation(Property.class).timeFormat().trim();
            }

            valueForField = timePattern.isEmpty()
                    ? Instant.parse(propertyValue)
                    : LocalDateTime.parse(propertyValue, DateTimeFormatter.ofPattern(timePattern))
                        .atZone(ZoneId.systemDefault())
                        .toInstant();
        }

        return valueForField;
    }
}