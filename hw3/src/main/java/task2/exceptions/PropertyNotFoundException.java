package task2.exceptions;

/**
 * Signals that an attempt to get a property has failed.
 */
public class PropertyNotFoundException extends Exception {
    /**
     * Constructs a {@code PropertyNotFoundException} with the specified detail message.
     * @param message detail message about the exception
     */
    public PropertyNotFoundException(String message) {
        super(message);
    }
}