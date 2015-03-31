package u6.u6a2;

public class StackFactory {
	public static IStack create()
	{
        return new ListStack();
	}
}
