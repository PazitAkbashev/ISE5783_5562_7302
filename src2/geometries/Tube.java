package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;


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
        Point p0 = axisray.getP0();
        Vector v = axisray.getDir();

        Vector p0_p = p.subtract(p0);
        double t = alignZero(p0_p.dotProduct(v));

        if(isZero(t)) return p0_p.normalize();

        Point o = p0.add((v.scale(t)));
        Vector o_p = p.subtract(o);
        return o_p.normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
