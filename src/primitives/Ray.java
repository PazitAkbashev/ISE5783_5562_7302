package primitives;

import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.isZero;

/**
 *
 */
public class Ray {
    private static final double DELTA = 0.1d;
    private final Point p0;
    private final Vector dir;

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
     * @param dir   direction of the ray
     * @param n     normal of the geometry at this point
     */
    public Ray(Point point, Vector dir, Vector n) {
        double nl = n.dotProduct(dir);
        Vector delta = n.scale(nl >= 0 ? DELTA : -DELTA);
        this.p0 = point.add(delta);
        //this.dir = dir.normalize(); //only for checking
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
     * Returns a new Point object that is a specific distance
     * along the ray from the starting point.
     *
     * @param t The distance from the starting point of the desired point on the ray.
     * @return A new Point object at distance t along the direction of the ray.
     */
    public Point getPoint(double t) {
        // if t is zero, return the starting point of the ray
//        if (isZero(t))  //in comment for checking only
//            return p0;
        return p0.add(dir.scale(t));
    }

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