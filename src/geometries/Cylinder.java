package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * this  class represent a Cylinder
 *
 * @author Pazit and Leah
 */
public class Cylinder extends Tube {

    final double length;

    @Override
    public List<Point> findIntersections(Ray ray) {
        return super.findIntersections(ray);
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

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
