package u12.u12a1;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Tests {
	private ISort<Integer> sort;

	@Before public void setup()
	{
		sort = SortFactory.create();
	}

	@Test public void empty()
	{
		ArrayList<Integer> result = new ArrayList<Integer>();
		sort.sort(result);
		Assert.assertTrue(result.isEmpty());
	}

	@Test public void single()
	{
		ArrayList<Integer> in = new ArrayList<Integer>();
		in.add(23);
		ArrayList<Integer> cp = new ArrayList<Integer>(in);
		sort.sort(cp);
		Assert.assertEquals(1, cp.size());
		Assert.assertEquals(in.get(0), cp.get(0));
	}

	@Test public void generic()
	{
		Random rand = new Random();
		final int size = 50000;
		ArrayList<Integer> in = new ArrayList<Integer>(size);
		for (int i=0; i<size; i++) {
			in.add(rand.nextInt());
		}
		ArrayList<Integer> cp = new ArrayList<Integer>(in);
		sort.sort(cp);
		Collections.sort(in);
		Assert.assertEquals(in.toString(), cp.toString());
	}
}
