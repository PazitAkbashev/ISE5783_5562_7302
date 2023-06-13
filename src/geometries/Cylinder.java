package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.alignZero;

/**
 * this  class represent a Cylinder
 *
 * @author Pazit and Leah
 */
public class Cylinder extends Tube {

    final double length;

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

        Point p0 = axisRay.getP0();
        Vector v = axisRay.getDir();

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
