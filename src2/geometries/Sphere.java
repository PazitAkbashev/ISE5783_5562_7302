package geometries;
import primitives.*;

import java.util.List;

/**
 * this class represent a sphere by center point and radius
 * @author  Pazit and Leah
 */
public class Sphere extends RadialGeometry{

    final Point center;

    /**
     * constructor for sphare in 3D
     * @param center is the center point of sphare
     * @param radius is the distance between the center point to point on the perimeter
     */
    public Sphere(Point center, double radius) {
        super(radius);
        this.center = center;
    }

    @Override
    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
