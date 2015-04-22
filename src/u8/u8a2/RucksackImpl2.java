package u8.u8a2;

import java.util.ArrayList;

/**
 * Created by Ferdinand on 22.04.2015.
 */
public class RucksackImpl2 implements IRucksack {
    @Override
    public Selection findBest(ArrayList<Integer> values, ArrayList<Integer> weights, int maxWeight) {
        assert (values.size() == weights.size());

        if (values.size() == 0 || weights.get(0) > maxWeight) {
            return new Selection(values.size(), 0);
        }

        //List wäre irgendwie eine bessere Idee als Signatur
        ArrayList<Integer> reducedValues = new ArrayList<>(values.size() - 1);
        reducedValues.addAll(values.subList(1, values.size()));
        ArrayList<Integer> reducedWeights = new ArrayList<>(weights.size() - 1);
        reducedWeights.addAll(weights.subList(1, weights.size()));

        Selection selection2 = findBest(reducedValues, reducedWeights, maxWeight - weights.get(0));
        selection2.setSize(selection2.size() + 1);
        selection2.setBits((selection2.bits() * 2) + 1);

        Selection selection1 = findBest(reducedValues, reducedWeights, maxWeight);
        selection1.setSize(selection1.size() + 1);
        selection1.setBits(selection1.bits() * 2);

        if (selection2.sum(values) < selection1.sum(values)) {
            return selection1;
        }
        return selection2;
    }
}
