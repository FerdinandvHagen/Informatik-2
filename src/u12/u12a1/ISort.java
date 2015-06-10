package u12.u12a1;

import java.util.ArrayList;

/**
 * Sort algorithms for comparable things
 *
 * @param <T>
 *            The type of the things which are sorted.
 */
public interface ISort<T extends Comparable<T>> {
	/**
	 * Sorts a array list of comparable things in place
	 *
	 * @param items
	 *            the array list of comparable things.
	 */
	public void sort(ArrayList<T> items);
}
