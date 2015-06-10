package u10.u10a1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Tests {
	private ISort<Integer> sort;

	@Before
	public void setup() {
		sort = SortFactory.create();
	}

	@Test
	public void empty() {
		ArrayList<Integer> result = sort.sort(new ArrayList<Integer>());
		Assert.assertTrue(result.isEmpty());
	}

	@Test
	public void single() {
		ArrayList<Integer> in = new ArrayList<Integer>();
		in.add(23);
		ArrayList<Integer> result = sort.sort(in);
		Assert.assertEquals(1, result.size());
		Assert.assertEquals(in.get(0), result.get(0));
	}

	@Test
	public void generic() {
		Random rand = new Random();
		final int size = 5000;
		ArrayList<Integer> in = new ArrayList<Integer>(size);
		for (int i = 0; i < size; i++) {
			in.add(rand.nextInt());
		}
		ArrayList<Integer> result = sort.sort(in);

		// copy list and sort it to compare result from MergeSort exercise with
		// a version from the Java Collections library.
		@SuppressWarnings("unchecked")
		ArrayList<Integer> compareWithResult = (ArrayList<Integer>) in.clone();
		Collections.sort(compareWithResult);

		Assert.assertEquals(compareWithResult.toString(), result.toString());
	}
}
