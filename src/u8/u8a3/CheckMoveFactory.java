package u8.u8a3;

public class CheckMoveFactory {
	public static ICheckMove create()
	{
		return new ImplCheckMove();
	}
}
