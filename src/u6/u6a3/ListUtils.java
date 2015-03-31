package u6.u6a3;

/**
 * Created by Ferdinand on 31.03.2015.
 */
public class ListUtils implements IListUtils {
    @Override
    public String toString(GenericList list) {
        if(list == null) {
            return "null";
        }

        return list.value.toString() + ", " + toString(list.next);
    }

    private String iterateToString(GenericList list) {
        if (list == null) {
            return "]";
        }

        return "," + list.value.toString() + iterateToString(list.next);
    }

    @Override
    public GenericList add(GenericList list, Object value) {
        return new GenericList(value, list);
    }

    @Override
    public int size(GenericList list) {
        if (list == null) {
            return 0;
        }

        return 1 + size(list.next);
    }

    @Override
    public GenericList sort(GenericList list) {
        if(list == null) {
            return null;
        }

        return insertSorted(sort(list.next), list.value);
    }

    public static GenericList insertSorted(GenericList list, Object value) {
        if (list == null) {
            return new GenericList(value, null);
        }

        if (((Comparable) value).smallerThan(((Comparable) list.value))) {
            return new GenericList(value, list);
        }

        return new GenericList(list.value, insertSorted(list.next, value));
    }
}
