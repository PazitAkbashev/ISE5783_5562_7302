package geometries;
import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;

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
        Point p0 = ray.getP0();
        Vector v = ray.getDir();
        Point center = this.center;
        double radius = this.radius;

        Vector u;
        try {
            u = center.subtract(p0);
        } catch (IllegalArgumentException ex) { // Ray starts at sphere center
            return List.of(p0.add(v.scale(radius)));
        }

        double tm = alignZero(v.dotProduct(u));
        double d = alignZero(Math.sqrt(u.lengthSquared() - tm * tm));

        // No intersections
        if (d >= radius) {
            return null;
        }

        double th = alignZero(Math.sqrt(radius * radius - d * d));

        // t1, t2 are the distances from p0 to the intersection points
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        // t1, t2 are negative or ray starts inside sphere
        if (t1 <= 0 && t2 <= 0) {
            return null;
        }

        // At least one intersection point
        List<Point> intersections = new ArryList<>();//***************************************88check, need to fix
        if (t1 > 0) {
            intersections.add(ray.getPoint(t1));
        }
        if (t2 > 0) {
            intersections.add(ray.getPoint(t2));
        }

        return intersections;
    }


}
