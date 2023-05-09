package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Class describe tube, the tube is infinity.
 * @author Pazit and Lea
 */
public class Tube extends RadialGeometry {
    final Ray axisray;

    /**
     * constructor of tube
     * @param radius  the radius of the tube
     * @param axisray ray, the ray contains the direction and the center point of the ray
     */
    public Tube(double radius, Ray axisray) {
        super(radius);
        this.axisray = axisray;
    }

    @Override
    public Vector getNormal(Point p) {

        Point p0 = axisray.getP0();
        Vector v = axisray.getDir();

        Vector p0_p = p.subtract(p0);
        double t = alignZero(p0_p.dotProduct(v));

        if(isZero(t))
            return p0_p.normalize();

        Point o = p0.add((v.scale(t)));
        Vector o_p = p.subtract(o);
        Vector n = o_p.normalize();
        return n;
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
