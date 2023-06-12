package primitives;

import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.isZero;

/**
 *
 */
public class Ray {
    private final Point p0;
    private final Vector dir;
    private static final double DELTA = 0.1d;

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
     * @param n normal of the geometry at this point
     */
    public Ray(Point point, Vector dir, Vector n) {
        double nl = n.dotProduct(dir);
        Vector delta = n.scale(nl > 0 ? DELTA : -DELTA);
        this.p0 = point.add(delta);
        this.dir = dir;
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

    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null :
                findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
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

        GeoPoint closestGeoPoint = null;
        double distance = Double.MAX_VALUE;
        double temp = 0;

        // checking the all points intersections
        for (GeoPoint p : intersections) {
            temp = p0.distanceSquared(p.point);
            if (temp < distance) {
                distance = temp;
                closestGeoPoint = p;
            }
        }
        return closestGeoPoint;
    }

    @Override
    /**
     * Checks if the current Ray object is equal to another object.
     *
     * @param o The object to compare for equality.
     * @return true if the objects are equal, false otherwise.
     */
    public boolean equals(Object o) {
        if (this == o) return true;  // If the objects are the same instance, they are equal
        if (!(o instanceof Ray)) return false;  // If the object is not of type Ray, they are not equal
        Ray ray = (Ray) o;  // Cast the object to Ray type for comparison
        return getP0().equals(ray.getP0()) && getDir().equals(ray.getDir());  // Compare the starting point and direction of the rays for equality
    }


    @Override
    public String toString() {
        return p0.toString() + dir.toString();
    }


}