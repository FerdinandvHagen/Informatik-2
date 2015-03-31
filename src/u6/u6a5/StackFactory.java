package u6.u6a5;

import u6.u6a2.IStack;

public class StackFactory {
	public static IStack create()
	{
        return new ChunkedStack();
	}
}
