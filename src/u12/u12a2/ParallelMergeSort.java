package u12.u12a2;

import u12.u12a1.HeapSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Ferdinand on 21.05.2015.
 */
public class ParallelMergeSort implements ISort {

    private final int maxThreads;

    public ParallelMergeSort(int i) {
        this.maxThreads = i;
    }

    public ParallelMergeSort() {
        this.maxThreads = 1;
    }

    public static void main(String[] args) {
        int[] data = new Random().ints(10000000).toArray();

        HeapSort heapSort = new HeapSort();
        ArrayList<Integer> list = new ArrayList<>(10000000);
        LinkedList<Integer> helper = new LinkedList<>();
        for(int i = 0; i<data.length; i++) {
            if(i % 1000000 == 0) {
                System.out.println(String.format("Ready: %d%%", (i / 100000)));
            }
            helper.add(data[i]);
        }
        list.addAll(helper);

        System.out.println("Starting Sort...");
        long time = System.currentTimeMillis();
        heapSort.sort(list);
        System.out.printf("Sorted with HS threads in %5dms%n", System.currentTimeMillis() - time);

        for (int i = 1; i < 6; i++) {
            ParallelMergeSort parallelMergeSort = new ParallelMergeSort(i);
            time = System.currentTimeMillis();
            parallelMergeSort.sort(Arrays.copyOf(data, data.length));
            System.out.printf("Sorted with %2d threads in %5dms%n", i, System.currentTimeMillis() - time);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static int[] sortImpl(int[] items) {
        if (items == null || items.length < 2) {
            return items;
        }

        final int half = items.length / 2;
        final int[] first = sortImpl(Arrays.copyOfRange(items, 0, half));
        final int[] second = sortImpl(Arrays.copyOfRange(items, half, items.length));

        int countera = 0;
        int counterb = 0;
        final int[] result = new int[first.length + second.length];
        for (int pos = 0; pos < items.length; pos++) {
            if (first.length == countera) {
                //first one is checked
                result[pos] = second[counterb];
                counterb++;
                continue;
            }

            if (second.length == counterb) {
                //second one is checked
                result[pos] = first[countera];
                countera++;
                continue;
            }

            if (first[countera] < second[counterb]) {
                result[pos] = first[countera];
                countera++;
            } else {
                result[pos] = second[counterb];
                counterb++;
            }
        }

        return result;
    }

    @Override
    public int[] sort(int[] items) {
        List<SortThread> threads = new ArrayList<>(maxThreads);
        double parts = items.length / (double) maxThreads;
        for (int i = 0; i < maxThreads; i++) {
            SortThread thread = new SortThread(Arrays.copyOfRange(items, (int) (i * parts), (int) (i * parts + parts)));
            thread.start();
            threads.add(thread);
        }

        for (SortThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return merge(threads);
    }

    private int[] merge(List<SortThread> threads) {
        if (threads.size() < 2) {
            return threads.get(0).result;
        }

        int[] first;
        int[] second;
        if (threads.size() > 2) {
            first = merge(threads.subList(0, threads.size() / 2));
            second = merge(threads.subList(threads.size() / 2, threads.size()));
        } else {
            first = threads.get(0).getResult();
            second = threads.get(1).getResult();
        }

        int countera = 0;
        int counterb = 0;
        final int[] result = new int[first.length + second.length];
        for (int pos = 0; pos < result.length; pos++) {
            if (first.length == countera) {
                //first one is checked
                result[pos] = second[counterb];
                counterb++;
                continue;
            }

            if (second.length == counterb) {
                //second one is checked
                result[pos] = first[countera];
                countera++;
                continue;
            }

            if (first[countera] < second[counterb]) {
                result[pos] = first[countera];
                countera++;
            } else {
                result[pos] = second[counterb];
                counterb++;
            }
        }

        return result;
    }

    private class SortThread extends Thread {
        private int[] result;
        private int[] objects;

        public SortThread(final int[] data) {
            super();
            this.objects = Arrays.copyOf(data, data.length);
        }

        public void run() {
            this.result = sortImpl(objects);
        }

        public int[] getResult() {
            return result;
        }
    }
}
