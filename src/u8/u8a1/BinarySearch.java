package u8.u8a1;

import java.util.List;

public class BinarySearch<Key extends Comparable<Key>, Value> implements
        IBinarySearch<Key, Value>, IMeasure {
    private int steps = 0;
    private int factor = 2;

    @Override
    public Value find(List<Unit<Key, Value>> haystack, Key needle) {
        ++steps;

        if (haystack.size() == 0) {
            return null;
        }

        Unit<Key, Value> middle = haystack.get(haystack.size() / factor);
        if (middle.key.equals(needle)) {
            return middle.value;
        }

        if (haystack.size() == 1) {
            return null;
        }

        if (needle.compareTo(middle.key) > 0) {
            //Find in right tree
            return find(haystack.subList(haystack.size() / factor + 1, haystack.size()), needle);
        }

        //Find in left
        return find(haystack.subList(0, haystack.size() / factor), needle);
    }

    @Override
    public void setFactor(int factor) {
        this.factor = factor;
    }

    @Override
    public int getNumberofCalls() {
        return steps;
    }
}
