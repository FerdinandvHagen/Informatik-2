package u2.u2a2;

import java.util.*;

/**
 * An array with random values.
 */
public class RandomArray {
    /**
     * internal storage of the numbers
     */
    private int numbers[];

    /**
     * Create a new array with random values between 0 (inclusive) and 1000 (exclusive).
     *
     * @param length the length of the new array.
     */
    public RandomArray(int length) {
        Random ran = new Random();
        numbers = ran.ints(length, 0, 1000).toArray();
    }

    /**
     * Return string-representation of array.
     * <p>
     * Example: the string-representation of int array[] = {1,2,3} is '[1, 2, 3]'
     *
     * @return a list of the values, separated by a comma and a space, and enclosed in squared brackets.
     */
    public String toString() {
        return Arrays.toString(numbers);
    }

    /**
     * recursive sort of the array in descending order in place.
     *
     * @param until value between 0 and length (inclusive) of {@link u2.u2a2.RandomArray#numbers}. <br>
     *              When the function returns the <em>until</em> largest values of {@link u2.u2a2.RandomArray#numbers}
     *              are placed in the first <em>until</em> positions of the array and sorted in descending order.
     */
    private void recursiveSort(int until) {
        // Leeres Array oder mit nur einem Knoten ist immer sortiert
        if (until == 0) {
            return;
        }

        //Sortiere die until - 1 objekte
        recursiveSort(until - 1);

        // Element an der until-1 ten Stelle tauschen mit dem größten Element im Array von [until-1, length] (not very nice but its a one liner :))
        swap(until - 1, Arrays.asList(Arrays.stream(Arrays.copyOfRange(numbers, until - 1, numbers.length)).boxed().toArray(size -> new Integer[size])).indexOf(Arrays.stream(Arrays.copyOfRange(numbers, until - 1, numbers.length)).max().getAsInt()) + until -1);
    }

    //Just a helper Method to swap to objects
    private void swap(int a, int b) {
        int i = numbers[b];
        numbers[b] = numbers[a];
        numbers[a] = i;
    }

    /**
     * sort the array in descending order in place.
     */
    public void sort() {
        recursiveSort(numbers.length);
    }
}
