package primitives;

import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.isZero;

/**
 * This class represents a ray in space as a starting point and a direction.
 *
 * @author Pazit and Leah - 26.06.23
 */
public class Ray {

    //the delta for moving the ray's head
    private static final double DELTA = 0.1d;

    //the starting point of the ray
    private final Point p0;

    //the direction of the ray
    private final Vector dir;

    /**
     * Constructs a new Ray object with a given starting point and direction.
     *
     * @param p0  The starting point of the ray as a Point object with x, y, and z coordinates.
     * @param dir The direction of the ray as a Vector object.
     */
    public Ray(Point p0, Vector dir) {
        //initialize the starting point of the ray
        this.p0 = p0;

        //initialize the direction vector of the ray
        this.dir = dir.normalize();
    }

    /**
     * Constructs a new Ray object with a given starting point and direction.
     * offset by the normal of the geometry
     *
     * @param point - original point
     * @param dir - direction of the ray
     * @param n - normal of the geometry at this point
     */
    public Ray(Point point, Vector dir, Vector n) {
        // calculate the dot product between the normal and the direction vector
        double nl = n.dotProduct(dir);

        // if the ray is orthogonal to the normal, move the ray's head by delta
        Vector delta = n.scale(nl >= 0 ? DELTA : -DELTA);

        //initialize the starting point of the ray
        this.p0 = point.add(delta);

        //initialize the direction vector of the ray
        this.dir = dir;
    }

    /**
     * getter for the starting point of the ray
     *
     * @return - The starting point of the ray.
     */
    public Point getP0() {
        return p0;
    }

    /**
     * getter for the direction vector of the ray
     *
     * @return - The direction of the ray.
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * Returns a new Point object that is a specific distance
     * along the ray from the starting point.
     *
     * @param t - The distance from the starting point of the desired point on the ray.
     * @return A new Point object at distance t along the direction of the ray.
     */
    public Point getPoint(double t) {
        // if t is zero, return the starting point of the ray
        if (isZero(t))
            return p0;
        return p0.add(dir.scale(t));
    }

    /**
     * find the closest point to the ray from a list of points.
     *
     * @param points - list of points
     * @return the closest point
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null :
                findClosestGeoPoint(points.stream().map
                        (p -> new GeoPoint(null, p)).toList()).getPoint();
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
        double temp;

        // checking the all points intersections
        for (GeoPoint p : intersections) {
            temp = p0.distanceSquared(p.getPoint());
            if (temp < distance) {
                distance = temp;
                closestGeoPoint = p;
            }
        }
        return closestGeoPoint;
    }

    @Override
    public boolean equals(Object o) {
        // If the objects are the same instance, they are equal
        if (this == o)
            return true;
        // If the object is not of type Ray, they are not equal
        if (!(o instanceof Ray))
            return false;
        // Cast the object to Ray type for comparison
        Ray ray = (Ray) o;
        // Compare the starting point and direction of the rays for equality
        return getP0().equals(ray.getP0()) && getDir().equals(ray.getDir());
    }

    @Override
    public String toString() {
        return p0.toString() + dir.toString();
    }
}