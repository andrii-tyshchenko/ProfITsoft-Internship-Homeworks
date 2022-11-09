package task1;

import java.util.Arrays;
import java.util.Comparator;

public class AppForPositiveNumbers {
    public static void main(String[] args) {
        Integer[] intArray = {5, 3, 8, 42, -157, 19, 1000, 4564655, -654, 0};

        try {
            Integer[] sortedPositiveIntArr = getSortedPositiveIntegers(intArray);

            for (int i : sortedPositiveIntArr) {
                System.out.println(i);
            }
        } catch(NullPointerException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Returns an array of descendant sorted positive Integers from given Integer array.
     * @param intArray - an array of positive and negative Integers.
     * @return the new array.
     */
    protected static Integer[] getSortedPositiveIntegers(Integer[] intArray) {
        if (intArray == null) {
            throw new NullPointerException("Input array must not be null");
        }

        return Arrays.stream(intArray)
                .filter(i -> i >= 0)
                .sorted(Comparator.reverseOrder())
                .toArray(Integer[]::new);
    }
}