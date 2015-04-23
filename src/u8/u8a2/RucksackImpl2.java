package u8.u8a2;

import java.util.ArrayList;

/**
 * Created by Ferdinand on 22.04.2015.
 */
public class RucksackImpl2 implements IRucksack {
    @Override
    public Selection findBest(ArrayList<Integer> values, ArrayList<Integer> weights, int maxWeight) {
        return findBest(values, weights, maxWeight, 0);
    }

    private Selection findBest(ArrayList<Integer> values, ArrayList<Integer> weights, int maxWeight, int depth) {
        if (values.size() == depth) {
            return new Selection(values.size(), 0);
        }

        Selection selection1 = findBest(values, weights, maxWeight, depth + 1);

        //Cut off second tree
        if(maxWeight < weights.get(depth)) {
            return selection1;
        }

        Selection selection2 = findBest(values, weights, maxWeight - weights.get(depth), depth + 1);
        selection2.set(depth, true);

        if (selection2.sum(values) < selection1.sum(values)) {
            return selection1;
        }
        return selection2;
    }
}
