package task2.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks field which value can be parsed from properties-file.
 * <p>Element {@code name} is used to set the name of property to be parsed, if it differs from the field name.
 * <p>Element {@code timeFormat} sets expected format of property value for Instant field.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Property {
    String name() default "";
    String timeFormat() default "";
}