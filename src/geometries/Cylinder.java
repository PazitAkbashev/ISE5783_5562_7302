package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
/**
 *  this  class represent a Cylinder
 * @author Pazit and Leah
 */
public class Cylinder extends Tube{

    final double length;

    /**
     * constructor for finite Cylinder
     * @param radius radius of the circumference of the cylunder
     * @param axisray ray of the circumference of the cylunder
     * @param length the length of the cylunder
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
