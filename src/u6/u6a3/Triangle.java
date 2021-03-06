package u6.u6a3;

public class Triangle extends GeometricObject {
	private int base;
	private int height;
	
	public String toString()
	{
		return String.format("Triangle(%d,%d)", base, height);
	}
	
	public int area()
	{
		return base * height / 2;
	}
	
	public Triangle(int base, int height)
	{
		this.base = base;
		this.height = height;
	}
}
