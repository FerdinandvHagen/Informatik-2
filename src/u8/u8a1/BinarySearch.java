package u8.u8a1;

import java.util.List;

public class BinarySearch<Key extends Comparable<Key>, Value> implements
		IBinarySearch<Key, Value>, IMeasure {
	@Override
	public Value find(List<Unit<Key, Value>> haystack, Key needle) {
		return null;
	}

	@Override
	public void setFactor(int factor) {

	}

	@Override
	public int getNumberofCalls() {
		return 0;
	}
}
