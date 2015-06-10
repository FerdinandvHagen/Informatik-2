package u10.u10a1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ferdinand on 08.05.2015.
 */
public class MergeSort<T extends Comparable<T>> implements ISort<T> {
    @Override
    public final ArrayList<T> sort(final ArrayList<T> items) {
        if (items == null || items.size() == 1 || items.isEmpty()) {
            return items;
        }

        if (items.size() == 2) {
            if (items.get(0).compareTo(items.get(1)) > 1) {
                final T item = items.get(0);
                items.set(0, items.get(1));
                items.set(1, item);

                return items;
            }
        }

        final int half = items.size() / 2;
        final List<T> first = this.sort(new ArrayList<>(items.subList(0, half)));
        final List<T> second = this.sort(new ArrayList<>(items.subList(half, items.size())));

        int countera = 0;
        int counterb = 0;
        final ArrayList<T> result = new ArrayList<>(items.size());
        for (int i = 0; i < items.size(); i++) {
            if (first.size() == countera) {
                //first one is checked completely
                result.addAll(second.subList(counterb, second.size()));
                break;
            }

            if (second.size() == counterb) {
                //second one is checked completely
                result.addAll(first.subList(countera, first.size()));
                break;
            }

            if (first.get(countera).compareTo(second.get(counterb)) < 0) {
                result.add(first.get(countera++));
            } else {
                result.add(second.get(counterb++));
            }
        }

        return result;
    }
}
