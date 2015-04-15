package u7.u7a2;

public class UtilsFactory {
	public static IBinarySearchTreeUtils<String> create()
	{
		return new ImplBinarySearchTreeUtils<String>();
	}
}
