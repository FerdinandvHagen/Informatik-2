package u10.u10a1;

import java.util.ArrayList;

/**
 * Sort algorithm for comparable things
 *
 * @param <T>
 *            The type of the things that are sorted.
 */
public interface ISort<T extends Comparable<T>> {
	/**
	 * Sorts an array list of comparable things
	 *
	 * @param items
	 *            Array list that contains comparable things. The list will not be altered.
	 * @return A copy of the given array list sorted in ascending order.
	 */
	public ArrayList<T> sort(ArrayList<T> items);
}
