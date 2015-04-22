package u8.u8a2;

import java.util.ArrayList;

/**
 * Created by Ferdinand on 22.04.2015.
 */
public class RucksackImpl implements IRucksack {
    @Override
    public Selection findBest(ArrayList<Integer> values, ArrayList<Integer> weights, int maxWeight) {
        assert (values.size() == weights.size());

        System.out.println(values);
        System.out.println(weights);

        Selection solution = new Selection(weights.size(), 0);
        for (int i = (int) Math.pow(2, weights.size()); i > 0; i--) {
            Selection selection = new Selection(weights.size());
            selection.setBits(i);
            if (selection.sum(weights) <= maxWeight && selection.sum(values) > solution.sum(values)) {
                solution.setBits(i);
            }
        }

        return solution;
    }
}
