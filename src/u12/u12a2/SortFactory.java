package u12.u12a2;

public class SortFactory {
	public static ISort create() {
		return new ParallelMergeSort();
	}
}
