package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * this class represent an infinite cylinder containing a radius and a ray
 * @author Pazit and Lea
 */
public class Tube extends RadialGeometry{
    final Ray axisray;

    /**
     * constructor of tube
     * @param radius radius of tube
     * @param axisray ray of tube
     */
    protected Tube(double radius, Ray axisray) {
        super(radius);
        this.axisray = axisray;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
