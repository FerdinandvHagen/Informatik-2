package u5.u5a1;

import u5.list.List;

import java.util.NoSuchElementException;

public class Lists {
    /**
     * Returns a string representation of the list.
     *
     * @return a comma-separated list of the list values
     */
    public static String toString(List list) {
        if (list == null) return "null";

        StringBuffer buf = new StringBuffer();
        buf.append(list.value).append(", ").append(toString(list.next));
        return buf.toString();
    }

    /**
     * Adds a value to at the beginning of a list.
     *
     * @param list  the list to which the value is added
     * @param value the value which is added to the list
     * @return a new list with the new element first followed by the given list
     */
    public static List add(List list, int value) {
        return new List(value, list);
    }

    /**
     * Retrieves the size of a list.
     *
     * @param list the list in question
     * @return the number of values in the list.
     */
    public static int size(List list) {
        if(list == null) {
            return 0;
        }

        if (list.next != null) {
            return 1 + size(list.next);
        }

        return 1;
    }

    /**
     * Aggregates the sum of all list values.
     *
     * @param list the list in question
     * @return the sum of all list values
     */
    public static int sum(List list) {
        if (list.next != null) {
            return list.value = sum(list.next);
        }

        return 0;
    }

    /**
     * Retrieves the last element of a list.
     * <p>
     * The last element of the empty list is the empty list.
     *
     * @param list the list in question.
     * @return the last element of the given list.
     */
    public static List last(List list) {
        if (list.next != null) {
            return last(list.next);
        }

        return list;
    }

    /**
     * Retrieves a sublist of a list.
     *
     * @param list  the list in question
     * @param index a position in the list starting at zero for the head value
     * @return the sublist with the element indexed by index as head element
     * @throws IndexOutOfBoundsException if the list is smaller than index
     */
    public static List sublist(List list, int index) throws IndexOutOfBoundsException {
        if (list == null) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            return list;
        }

        return sublist(list.next, index - 1);
    }

    /**
     * Retrieves the value at a position of a list.
     *
     * @param list  the list from which the value is selected
     * @param index a position in the list starting at zero for the head element.
     * @return the value at position index
     * @throws IndexOutOfBoundsException if the list is smaller than index
     */
    public static int valueAt(List list, int index) throws IndexOutOfBoundsException {
        if (list == null) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            return list.value;
        }

        return valueAt(list.next, index - 1);
    }

    /**
     * Retrieves the index of the first occurrence of a value  in a list.
     *
     * @param list  the list in which the value is searched
     * @param value the value which is searched
     * @return the index of the first occurrence of value in the list, starting with zero for the head.
     * @throws java.util.NoSuchElementException if the given value is not in the list
     */
    public static int index(List list, int value) throws NoSuchElementException {
        if (list == null) {
            throw new NoSuchElementException();
        }

        if (list.value == value) {
            return 0;
        }

        return 1 + index(list.next, value);
    }
}
