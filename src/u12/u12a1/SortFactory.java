package u12.u12a1;

public class SortFactory {
	public static ISort<Integer> create() {
		return new HeapSort<>();
	}
}
