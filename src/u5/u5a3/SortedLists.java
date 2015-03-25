package u5.u5a3;

import u5.list.List;

public class SortedLists {
    /**
     * Inserts a value into a sorted list so that the resulting list is still sorted.
     * The sort order is ascending.
     *
     * @param list  a sorted list.
     *              After the operation the state of this list is undefined.
     *              Don't use it any more. Use the returned list instead.
     * @param value the value which is inserted into the list
     * @return
     */
    public static List insertSorted(List list, int value) {
        if (list == null) {
            return new List(value, null);
        }

        if (list.value > value) {
            return new List(value, list);
        }

        return new List(list.value, insertSorted(list.next, value));
    }

    /**
     * Sorts a list in ascending order.
     *
     * @param list the list which is sorted.
     *             After the operation the state of this list is undefined.
     *             Don't use it any more. Use the returned list instead.
     * @return the sorted variant of the given list
     */
    public static List sort(List list) {
        return insertSorted(list.next, list.value);
    }
}
