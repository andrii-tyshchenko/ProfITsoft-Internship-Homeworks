package task1;

import java.util.Arrays;
import java.util.Comparator;

public class AppForPositiveNumbers {
    public static void main(String[] args) {
        Integer[] intArr = {5, 3, 8, 42, -157, 19, 1000, 4564655, -654, 0};

        Integer[] sortedPositiveIntArr = getSortedPositiveIntegers(intArr);

        for (int i : sortedPositiveIntArr) {
            System.out.println(i);
        }
    }

    /**
     * Returns an array of descendant sorted positive Integers from given Integer array.
     * @param intArray - an array of positive and negative Integers.
     * @return the new array.
     */
    private static Integer[] getSortedPositiveIntegers(Integer[] intArray) {
        return Arrays.stream(intArray)
                .filter(i -> i >= 0)
                .sorted(Comparator.reverseOrder())
                .toArray(Integer[]::new);
    }
}