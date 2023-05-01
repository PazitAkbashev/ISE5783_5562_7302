package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
import java.util.List;

/**
 * Class describe cylinder not infinite tube.
 * @author Pazit and Lea
 */
public class Cylinder extends Tube {

    final double length;

    /**
     * constructor for finite Cylinder
     * @param radius  radius of the circumference of the cylunder
     * @param axisray the ray contains the direction and the center point of the center
     * @param length  the length of the cylunder
     * @throws IllegalArgumentException if length or radius are negative or zero
     *
     */
    protected Cylinder(double radius, Ray axisray, double length) {
        super(radius, axisray);
        if (length < 0 || Util.isZero(length)) {
            throw new IllegalArgumentException("length can't be zero or negative.");
        }
        this.length = length;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return super.findIntersections(ray);
    }

}
