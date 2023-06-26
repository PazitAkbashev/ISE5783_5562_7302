package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * this class represent a sphere by center point and radius
 *
 * @author Pazit and Leah - 26.06.23
 */
public class Sphere extends RadialGeometry {

    //the center point of the sphere
    private final Point center;

    /**
     * constructor for sphere in 3D
     *
     * @param center is the center point of sphere
     * @param radius is the distance between the center point to point on the perimeter
     */
    public Sphere(Point center, double radius) {
        //initialization of the radius by fathers constructor
        super(radius);

        //initialization of the center point
        this.center = center;
    }

    @Override
    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        Point P0 = ray.getP0();
        Vector v = ray.getDir();

        if (P0.equals(center)) {
            if(alignZero(radius - maxDistance) > 0){
                return null;
            }
            return List.of(new GeoPoint(this, center.add(v.scale(radius))));
        }

        Vector U = center.subtract(P0);

        double tm = alignZero(v.dotProduct(U));
        double d = alignZero(Math.sqrt(U.lengthSquared() - tm * tm));

        // no intersections : the ray direction is above the sphere
        if (d >= radius) {
            return null;
        }

        double th = alignZero(Math.sqrt(radius * radius - d * d));

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        if(t1 <=0 && t2 <= 0){
            return null;
        }

        if (t1 > 0 && t2 > 0 && alignZero(t1 - maxDistance) <= 0 && alignZero(t2 - maxDistance) <= 0) {
//            Point P1 = P0.add(v.scale(t1));
//            Point P2 = P0.add(v.scale(t2));
            Point P1 =ray.getPoint(t1);
            Point P2 =ray.getPoint(t2);
            return List.of(new GeoPoint(this,P1), new GeoPoint(this,P2));
        }
        if (t1 > 0  && alignZero(t1 - maxDistance) <= 0) {
            //Point P1 = P0.add(v.scale(t1));
            Point P1 =ray.getPoint(t1);
            return List.of(new GeoPoint(this,P1));
        }
        if (t2 > 0 && alignZero(t2 - maxDistance) <= 0) {
            // Point P2 = P0.add(v.scale(t2));
            Point P2 =ray.getPoint(t2);
            return List.of(new GeoPoint(this,P2));
        }
        return null;
    }
//
//        @Override
//    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
//        // Starting point of the ray
//        Point p0 = ray.getP0();
//        // Direction of the ray
//        Vector v = ray.getDir();
//
//        // Check if the starting point of the ray is the same as the center of the sphere
//        if (p0.equals(center)) {
//            if(alignZero(radius - maxDistance ) >= 0)
//                return null;
//            return List.of(new GeoPoint(this, ray.getPoint(radius)));
//
//        }
//        // Vector from the starting point of the ray to the center of the sphere
//        Vector u = center.subtract(p0);
//        // The projection of u onto v
//        double tm = alignZero(u.dotProduct(v));
//        // Distance between the starting point and the sphere's center projected on v
//        double d = alignZero(Math.sqrt(u.lengthSquared() - (tm * tm)));
//
//        // If the distance is greater than or equal to the radius, there are no intersection points
//        if (d >= radius) {
//            return null;
//        }
//
//        // Height of the right triangle formed by the intersection points and the radius
//        double th = alignZero(Math.sqrt((radius * radius) - (d * d)));
//
//        // Distance along the ray to the first intersection point
//        double t1 = alignZero(tm - th);
//        // Distance along the ray to the second intersection point
//        double t2 = alignZero(tm + th);
//
//        // Check if both intersection points are valid within the maximum distance
//        if (t1 > 0 && t2 > 0 && alignZero(t1 - maxDistance) < 0 && alignZero(t2 - maxDistance) < 0) {
//            Point p1 = ray.getPoint(t1);  // First intersection point
//            Point p2 = ray.getPoint(t2);  // Second intersection point
//
//            // Return both intersection points
//            return List.of(new GeoPoint(this, p1), new GeoPoint(this, p2));
//        }
//
//        // Check if the first intersection point is valid within the maximum distance
//        if (t1 > 0 && alignZero(t1 - maxDistance) < 0) {
//            // Return the first intersection point
//            return List.of(new GeoPoint(this, ray.getPoint(t1)));
//        }
//
//        // Check if the second intersection point is valid within the maximum distance
//        if (t2 > 0 && alignZero(t2 - maxDistance) < 0) {
//            // Return the second intersection point
//            return List.of(new GeoPoint(this, ray.getPoint(t2)));
//        }
//        // No valid intersection points found
//        return null;
//    }
}