package u10.u10a1;

import java.util.ArrayList;
import java.util.List;

public class SortFactory {
    public static ISort<Integer> create() {
        return new MergeSort<>();
    }

    public static void main(String[] args) {
        //Fill array with 51200 numbers
        ArrayList<Integer> numbers = new ArrayList<>(51200);
        for (int i = 0; i < 51200; i++) {
            numbers.add((int) (Math.random() * 51200.0));
        }

        runCal((ArrayList<Integer>) numbers.clone(), 100);
        runCal((ArrayList<Integer>) numbers.clone(), 200);
        runCal((ArrayList<Integer>) numbers.clone(), 400);
        runCal((ArrayList<Integer>) numbers.clone(), 800);
        runCal((ArrayList<Integer>) numbers.clone(), 1600);
        runCal((ArrayList<Integer>) numbers.clone(), 3200);
        runCal((ArrayList<Integer>) numbers.clone(), 6400);
        runCal((ArrayList<Integer>) numbers.clone(), 12800);
        runCal((ArrayList<Integer>) numbers.clone(), 25600);
        runCal((ArrayList<Integer>) numbers.clone(), 51200);
    }

    private static void runCal(ArrayList<Integer> numbers, int count) {
        //6400
        ArrayList<Long> timeTable = new ArrayList<>(20);
        for (int i = 0; i < 202; i++) {
            Long time = System.nanoTime();

            create().sort(new ArrayList<>(numbers.subList(0, count)));

            timeTable.add(System.nanoTime() - time);
        }

        System.out.printf("%5d: %10d us%n", count, calculateAvg(timeTable));
    }

    private static Long calculateAvg(final List<Long> times) {
        Long max = Long.MIN_VALUE;
        Long min = Long.MAX_VALUE;
        Long sum = 0L;

        for (final Long time : times) {
            sum += time;

            max = Math.max(max, time);
            min = Math.min(min, time);
        }

        return (sum - max - min) / (times.size() - 2) / 1000;
    }
}
