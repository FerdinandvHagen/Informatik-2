package u8.u8a1;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Tests {
	private IBinarySearch<Integer, String> search;
	
	@Before public void setup()
	{
		search = BinarySearchFactory.create();
	}
	
	private void runTest(int item, String expected, int... items)
	{
		List<Unit<Integer, String>> haystack = new ArrayList<Unit<Integer, String>>(items.length);
		for (int i: items) {
			haystack.add(new Unit<Integer, String>(i, String.valueOf(i)));
		}
		String result = search.find(haystack, item);
		assertEquals(expected, result);
	}
	
	@Test public void empty() {	runTest(1, null); }
	@Test public void notFound() { runTest(0, null, 1, 2, 3, 4); }
	@Test public void single() { runTest(1, "1", 1); }
	@Test public void even() { runTest(1, "1", 0, 1, 2, 3); }
	@Test public void odd() { runTest(1, "1", 0, 1, 2, 3, 4); }
	@Test public void example() { runTest(78, "78", 3, 7, 17, 25, 33, 47, 56, 62, 65, 66, 68, 70, 78, 89, 92); }
	@Test public void generic() 
	{
		List<Unit<Integer, String>> haystack = new ArrayList<Unit<Integer, String>>();
		int count = 100;
		for (int i=0; i<count; i++) {
			if (i %3 == 0) continue;
			haystack.add(new Unit<Integer, String>(i, String.valueOf(i)));
		}
		
		for (int i=0; i<count; i++) {
			if (i%3 == 0) continue;
			String result = search.find(haystack, i);
			assertEquals(String.valueOf(i), result);
		}
		
		for (int i=0; i<=count; i++) {
			if (i%3 == 0) {
				String result = search.find(haystack, i);
				assertEquals(null, result);
			}
		}
	}

	@Test
	public void test1A() {
		List<Unit<Integer, String>> arraya = new ArrayList<>(15);
		arraya.add(new Unit<>(3, "3"));
		arraya.add(new Unit<>(7, "7"));
		arraya.add(new Unit<>(17, "17"));
		arraya.add(new Unit<>(25, "25"));
		arraya.add(new Unit<>(33, "33"));
		arraya.add(new Unit<>(47, "47"));
		arraya.add(new Unit<>(56, "56"));
		arraya.add(new Unit<>(62, "62"));
		arraya.add(new Unit<>(65, "65"));
		arraya.add(new Unit<>(66, "66"));
		arraya.add(new Unit<>(68, "68"));
		arraya.add(new Unit<>(70, "70"));
		arraya.add(new Unit<>(78, "78"));
		arraya.add(new Unit<>(89, "89"));
		arraya.add(new Unit<>(92, "92"));

		// mittlere Anzahl rekursiver Aufrufe wenn man nach allen:

		// vorhandenen Zahlen sucht?
		//B)
		BinarySearch<Integer, String> mysearch = new BinarySearch<>();
		for(Unit<Integer,String> unit : arraya) {
			assertEquals(unit.value, mysearch.find(arraya, unit.key));
		}
		System.out.println("alle vorhanden Zahlen: b " + mysearch.getNumberofCalls() / arraya.size());

		//C)
		mysearch = new BinarySearch<>();
		mysearch.setFactor(3);
		for(Unit<Integer,String> unit : arraya) {
			assertEquals(unit.value, mysearch.find(arraya, unit.key));
		}
		System.out.println("alle vorhanden Zahlen: c " + mysearch.getNumberofCalls() / arraya.size());

		//Zahlen von 0 bis 99
		//B)
		mysearch = new BinarySearch<>();
		for(int i = 0; i<100; i++) {
			mysearch.find(arraya, i);
		}
		System.out.println("alle Zahlen von 0 bis 99: b " + mysearch.getNumberofCalls()/100);

		//C)
		mysearch = new BinarySearch<>();
		mysearch.setFactor(3);
		for(int i = 0; i<100; i++) {
			mysearch.find(arraya, i);
		}
		System.out.println("alle Zahlen von 0 bis 99: c " + mysearch.getNumberofCalls()/100);

		//Zahlen von 0 bis 9
		//B)
		mysearch = new BinarySearch<>();
		for(int i = 0; i<10; i++) {
			mysearch.find(arraya, i);
		}
		System.out.println("alle Zahlen von 0 bis 9: b " + mysearch.getNumberofCalls()/10);

		//C)
		mysearch = new BinarySearch<>();
		mysearch.setFactor(3);
		for(int i = 0; i<10; i++) {
			mysearch.find(arraya, i);
		}
		System.out.println("alle Zahlen von 0 bis 9: c " + mysearch.getNumberofCalls()/10);
	}
}
