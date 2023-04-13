package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;


/**
 * this class represent an infinite cylinder containing a radius and a ray
 *
 * @author Pazit and Lea
 */
public class Tube extends RadialGeometry {
    final Ray axisray;

    /**
     * constructor of tube
     *
     * @param radius  radius of tube
     * @param axisray ray of tube
     */
    protected Tube(double radius, Ray axisray) {
        super(radius);
        this.axisray = axisray;
    }

    @Override
    public Vector getNormal(Point p) {
        Point p0 = axisRay.getP0();
        Vector v = axisRay.getDir();

        Vector p0_p = p.subtract(p0);
        double t = alignZero(p0_p.dotProduct(v));

        if(isZero(t)) return p0_p.normalize();

        Point o = p0.add((v.scale(t)));
        Vector o_p = p.subtract(o);
        return o_p.normalize();
    }
}
