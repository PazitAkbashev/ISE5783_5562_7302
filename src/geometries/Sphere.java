package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

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
    /**
     * Helper method to find intersection points between a ray and a sphere.
     *
     * @param ray         The ray to find intersections with.
     * @param maxDistance The maximum distance for valid intersections.
     * @return A list of GeoPoint objects representing the intersection points, or null if no intersections are found.
     */
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        Point p0 = ray.getP0();      // Starting point of the ray
        Vector v = ray.getDir();     // Direction of the ray

        // Check if the starting point of the ray is the same as the center of the sphere
        if (p0.equals(center)) {
            throw new IllegalArgumentException("ray p0 cannot be equal to the center of the sphere");
        }

        Vector u = center.subtract(p0);  // Vector from the starting point of the ray to the center of the sphere
        double tm = alignZero(u.dotProduct(v));  // The projection of u onto v
        double d = alignZero(Math.sqrt(u.lengthSquared() - (tm * tm)));  // Distance between the starting point and the sphere's center projected on v

        // If the distance is greater than or equal to the radius, there are no intersection points
        if (d >= radius) {
            return null;
        }

        double th = alignZero(Math.sqrt((radius * radius) - (d * d)));  // Height of the right triangle formed by the intersection points and the radius

        double t1 = alignZero(tm - th);  // Distance along the ray to the first intersection point
        double t2 = alignZero(tm + th);  // Distance along the ray to the second intersection point

        // Check if both intersection points are valid within the maximum distance
        if (t1 > 0 && t2 > 0 && alignZero(t1 - maxDistance) <= 0 && alignZero(t2 - maxDistance) <= 0) {
            Point p1 = ray.getPoint(t1);  // First intersection point
            Point p2 = ray.getPoint(t2);  // Second intersection point

            return List.of(new GeoPoint(this, p1), new GeoPoint(this, p2));  // Return both intersection points
        }

        // Check if the first intersection point is valid within the maximum distance
        if (t1 > 0 && alignZero(t1 - maxDistance) <= 0) {
            return List.of(new GeoPoint(this, ray.getPoint(t1)));  // Return the first intersection point
        }

        // Check if the second intersection point is valid within the maximum distance
        if (t2 > 0 && alignZero(t2 - maxDistance) <= 0) {
            return List.of(new GeoPoint(this, ray.getPoint(t2)));  // Return the second intersection point
        }

        return null;  // No valid intersection points found
    }

}
