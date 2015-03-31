package u6.u6a4;

/**
 * Factory for "Testat" filters 
 */
public class FilterFactory {
	/**
	 * Create a "Testat" filter
	 * @return a "Testat" filter
	 */
	public static IFilter create()
	{
		return new Filter();
	}
}
