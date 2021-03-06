package u6.u6a3;

/**
 * abstract class of geometric objects
 */
public abstract class GeometricObject implements Comparable {
	public abstract int area();
	
	public boolean smallerThan(Comparable rhs) 
    {
        GeometricObject geometricObject = (GeometricObject) rhs;
		return area() < geometricObject.area();
	}
}
