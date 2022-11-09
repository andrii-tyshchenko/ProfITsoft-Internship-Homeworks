package task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static task1.AppForPositiveNumbers.*;

class AppForPositiveNumbersTest {
    private Integer[] intArr;
    private Integer[] expectedPositiveIntArr;

    @Test
    @DisplayName("Standard test")
    public void testGetSortedPositiveIntegers() {
        intArr = new Integer[]{2, 500, 0, -1234, 777, -1, 42};

        expectedPositiveIntArr = new Integer[]{777, 500, 42, 2, 0};

        assertArrayEquals(expectedPositiveIntArr, getSortedPositiveIntegers(intArr));
    }

    @Test
    @DisplayName("Test for null")
    public void testGetPositiveIntegersFromNull() {
        intArr = null;

        Exception actualException = assertThrows(NullPointerException.class, ()-> getSortedPositiveIntegers(intArr));

        String expectedExceptionMessage = "Input array must not be null";

        assertEquals(expectedExceptionMessage, actualException.getMessage());
    }

    @Test
    @DisplayName("Test for boundary values")
    public void testGetPositiveIntegersFromEmptyArray() {
        intArr = new Integer[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE};

        expectedPositiveIntArr = new Integer[]{Integer.MAX_VALUE, 0};

        assertArrayEquals(expectedPositiveIntArr, getSortedPositiveIntegers(intArr));
    }
}