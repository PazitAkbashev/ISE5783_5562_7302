package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * This class represent a Cylinder - finite tube.
 * cylinder contains radius (- in RadialGeometry class),
 * axisRay (- in Tube class)
 * top and base cups
 * and length
 *
 * @author Pazit and Leah - 26.06.23
 */
public class Cylinder extends Tube {
    //the length - the height of the cylinder
    private final double length;

    //the base of the cylinder
    private final Plane base;

    //the top base of the cylinder
    private final Plane top;

    /**
     * constructor for finite Cylinder
     *
     * @param radius - radius of the circumference of the cylinder
     * @param axisray - ray of the circumference of the cylinder
     * @param length - the length of the cylinder
     */
    public Cylinder(double radius, Ray axisray, double length) {
        //call to Tube constructor (and it calls to RadialGeometry constructor)
        super(radius, axisray);

        Point p1 = axisray.getP0().add(axisray.getDir().scale(length));
        Point p0 = axisRay.getP0();
        //initialize the length
        this.length = length;
        this.base = new Plane(p1, axisRay.getDir().scale(-1));
        this.top = new Plane(p0, axisRay.getDir());
    }

    //----BONUS----,
    //otherwise return null
    @Override
    public Vector getNormal(Point point) {
        //the direction of the axis ray
        Vector v= axisRay.getDir();

        //the starting point of the axis ray
        Point p0 =axisRay.getP0();

        //if p=p0, then (p-p0) is zero vector
        //returns the vector of the base as a normal
        if(point.equals(p0)){
            return v.scale(-1);
        }

        double t= v.dotProduct(point.subtract(p0));

        //check if the point on the bottom
        if(isZero(t)){
            return v.scale(-1);
        }
        //check if the point on the top
        if(isZero(t-length)){
            return v;
        }
        Point o=p0.add(v.scale(t));
        Vector o_p = point.subtract(o).normalize();
        return o_p;
    }
}
