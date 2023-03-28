package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 *  this a class represent a Cylinder
 * @author Pazit and Leah
 */
public class Cylinder extends Tube{

    final double length;

    /**
     * constructor for finite Cylinder
     * @param radius radius of the circumference of the cylunder
     * @param axisray
     * @param length
     */
    protected Cylinder(double radius, Ray axisray,double  length) {
        super(radius, axisray);
        this.length = length;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
