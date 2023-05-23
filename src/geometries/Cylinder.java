package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * this  class represent a Cylinder
 *
 * @author Pazit and Leah
 */
public class Cylinder extends Tube {

    final double length;

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return super.findGeoIntersections(ray);
    }

    /**
     * constructor for finite Cylinder
     *
     * @param radius  radius of the circumference of the cylinder
     * @param axisray ray of the circumference of the cylinder
     * @param length  the length of the cylinder
     */
    protected Cylinder(double radius, Ray axisray, double length) {
        super(radius, axisray);
        this.length = length;
    }

    //bonus, otherwise returned null
    @Override
    public Vector getNormal(Point point) {

        Point p0 = axisray.getP0();
        Vector v = axisray.getDir();

        Vector p0_p = point.subtract(p0);
        double t = alignZero(p0_p.dotProduct(v));

        //if point is on one of the bases of the cylinder
        if (t == length || t == 0) {
            return v.normalize();
        }

        Point o = p0.add((v.scale(t)));
        Vector o_p = point.subtract(o);
        return o_p.normalize();
    }
}
