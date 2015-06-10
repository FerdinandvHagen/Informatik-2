package u12.u12a2;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import java.util.Random;
import java.util.Arrays;

public class Tests {
	private ISort sort;

	@Before public void setup()
	{
		sort = SortFactory.create();
	}

	@Test public void generic()
	{
		Random rand = new Random();
		final int size = 10;
		int[] in = new int[size];
		int[] cp = new int[size];
		for (int i=0; i<size; i++) {
			in[i] = rand.nextInt();
			cp[i] = in[i];
		}

		int[] out = sort.sort(in);
		Arrays.sort(cp);
		Assert.assertEquals(Arrays.toString(cp), Arrays.toString(out));
	}
}
