package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * this class represent a sphere by center point and radius
 *
 * @author Pazit and Leah
 */
public class Sphere extends RadialGeometry {

    final Point center;

    /**
     * constructor for sphere in 3D
     *
     * @param center is the center point of sphere
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

        if (p0.equals(center)) {
            throw new IllegalArgumentException("ray p0 cannot be equals to the center of the sphere");
        }

        Vector u = center.subtract(p0);
        double tm = alignZero(u.dotProduct(v));
        double d = alignZero(Math.sqrt(u.lengthSquared() - (tm * tm)));

        // there is no intersections points
        if (d >= radius) {
            return null;
        }

        double th = alignZero(Math.sqrt((radius * radius) - (d * d)));

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        if (t1 > 0 && t2 > 0) {
            Point p1 = ray.getPoint(t1);
            Point p2 = ray.getPoint(t2);

            return List.of(p1, p2);
        }

        if (t1 > 0) {
            return List.of(ray.getPoint(t1));
        }

        if (t2 > 0) {
            return List.of(ray.getPoint(t2));
        }

        return null; // 0 points
    }
}
