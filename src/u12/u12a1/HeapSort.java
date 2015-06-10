package u12.u12a1;

import java.util.ArrayList;

/**
 * Created by Ferdinand on 21.05.2015.
 */
public class HeapSort<T extends Comparable<T>> implements ISort<T> {
    @Override
    public void sort(ArrayList<T> items) {
        //Aufbauen
        for (int i = 0; i < items.size(); i++) {
            insert(items, i);
        }

        //GetMin und zuweisen
        for (int i = items.size() - 1; i > 0; i--) {
            T min = getMin(items, i);
            items.set(i, min);
        }

        //Finally switch every position
        for (int i = 0; i < items.size() / 2; i++) {
            swap(items, i, items.size() - i - 1);
        }
    }

    private T getMin(ArrayList<T> items, int i) {
        final T min = items.get(0);
        items.set(0, items.get(i));

        this.bringItemToLowestPosition(items, 0, i);

        return min;
    }

    private void bringItemToLowestPosition(ArrayList<T> items, int curPos, int maxDepth) {
        int j = this.successor(curPos);
        if (j < maxDepth) {
            if (j + 1 < maxDepth && items.get(j + 1).compareTo(items.get(j)) < 0) {
                j++;
            }

            if (items.get(curPos).compareTo(items.get(j)) > 0) {
                this.swap(items, curPos, j);
                this.bringItemToLowestPosition(items, j, maxDepth);
            }
        }
    }

    private int successor(int curPos) {
        return (curPos << 1) + 1;
    }

    private void insert(ArrayList<T> items, int i) {
        if (i != 0 && items.get(precessor(i)).compareTo(items.get(i)) > 0) {
            swap(items, precessor(i), i);
            insert(items, precessor(i));
        }
    }

    private void swap(ArrayList<T> items, int precessor, int i) {
        T spare = items.get(precessor);
        items.set(precessor, items.get(i));
        items.set(i, spare);
    }

    private int precessor(int i) {
        return (i - 1) / 2;
    }
}
