package u8.u8a2;

public class RucksackFactory {
	public static IRucksack create() {
		return new RucksackImpl();
	}
}
