package primitives;

import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.isZero;

/**
 * - This class represents a ray, defined by a starting point and a direction vector.
 * <p>
 * - The Ray class has methods for getting the starting point and direction of the ray,
 * and for calculating a new point along the ray given a specific distance.
 * <p>
 * - The class also overrides the equals() method to check if two Ray objects are equal by comparing their
 * starting point and direction.
 * <p>
 * - It also provides a toString() method that returns a string representation of the ray in the form of its
 * starting point and direction.
 * <p>
 * - The class requires a Point object to define the starting point and a Vector object to define the direction.
 *
 * @author Pazit and Leah
 */
public class Ray {
    private final Point p0;
    private final Vector dir;

    private static final double DELTA = 0.1;

//    /**
//     * find the closest point to the ray from a list of points.
//     *
//     * @param intersections list of points
//     * @return the closest point
//     */
//    public Point findClosestPoint(List<Point> intersections) {
//        if (intersections == null) {
//            return null;
//        }
//        Point result = null;
//        double distance = Double.MAX_VALUE;
//        double temp = 0;
//
//        // checking the all points intersections
//        for (Point p : intersections) {
//            temp = p0.distance(p);
//            if (temp < distance) {
//                distance = temp;
//                result = p;
//            }
//        }
//        return result;
//    }

    /**
     * Constructs a new Ray object with a given starting point and direction.
     *
     * @param p0  The starting point of the ray as a Point object with x, y, and z coordinates.
     * @param dir The direction of the ray as a Vector object.
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    /**
     * Constructs a new Ray object with a given starting point and direction.
     * offset by the normal of the geometry
     *
     * @param point original point
     * @param l direction of the ray
     * @param n normal of trhe geometry at this point
     */
    public Ray(Point point, Vector l, Vector n) {
        double nl = n.dotProduct(l);
        Vector delta = n.scale(nl > 0 ? DELTA : -DELTA);
        this.p0 = point.add(delta);
        this.dir = l;


    }

    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null :
                findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    /**
     * Returns the starting point of the ray as a Point object.
     *
     * @return The starting point of the ray.
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Returns the direction of the ray as a Vector object.
     *
     * @return The direction of the ray.
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * Returns a new Point object that is a specific distance along the ray from the starting point.
     *
     * @param t The distance from the starting point of the desired point on the ray.
     * @return A new Point object at distance t along the direction of the ray.
     */
    public Point getPoint(double t) {
        // if t is zero, return the starting point of the ray
        if (isZero(t))
            return p0;
        return p0.add(dir.scale(t));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray)) return false;
        Ray ray = (Ray) o;
        return getP0().equals(ray.getP0()) && getDir().equals(ray.getDir());
    }

    @Override
    public String toString() {
        return p0.toString() + dir.toString();
    }

    /**
     * find the closest point to the ray from a list of points.
     *
     * @param intersections list of points
     * @return the closest point
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> intersections) {
        if (intersections == null) {
            return null;
        }
        GeoPoint result = null;
        double distance = Double.MAX_VALUE;
        double temp = 0;

        // checking the all points intersections
        for (GeoPoint p : intersections) {
            temp = p0.distanceSquared(p.point);
            if (temp < distance) {
                distance = temp;
                result = p;
            }
        }
        return result;
    }
}