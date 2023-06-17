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
                        (p -> new GeoPoint(null, p)).toList()).point;
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
            temp = p0.distanceSquared(p.point);
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

//public class Ray {
//    private final Point p0;
//    private final Vector dir;
//    private static final double DELTA = 0.1;
//
//    /**
//     * A ctor to initialize a Ray with a Point and a direction Vector.
//     *
//     * @param p0  The head Point of the Ray
//     * @param dir The direction Vector of the Ray
//     */
//    public Ray(Point p0, Vector dir) {
//        dir = dir.normalize();
//        this.p0 = p0;
//        this.dir = dir;
//    }
//
//    /**
//     * A ctor to initialize a ray with a point, a direction, and a vector for
//     * DELTA moving
//     *
//     * @param head the ray head
//     * @param l    the direction
//     * @param n    the normal
//     */
//    public Ray(Point head, Vector l, Vector n) {
//        double nl = n.dotProduct(l);
//        p0 = isZero(nl) ? head : head.add(n.scale(nl < 0 ? -DELTA : DELTA));
//        dir = l.normalize();
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj instanceof Ray other)
//            return p0.equals(other.p0) && dir.equals(other.dir);
//        return false;
//    }
//
//    @Override
//    public String toString() {
//        return "Ray{p0: " + p0 + " | dir: " + dir + "}";
//    }
//
//    /**
//     * Getter for the head point of the Ray
//     *
//     * @return the head point
//     */
//    public Point getP0() {
//        return p0;
//    }
//
//    /**
//     * @return the direction Vector of the Ray
//     */
//    public Vector getDir() {
//        return dir;
//    }
//
//    /**
//     * Calculates a point on the ray line at a given distance from the head
//     *
//     * @param t the distance
//     * @return a point on the ray, according to t
//     */
//    public Point getPoint(double t) {
//        return isZero(t) ? p0 : p0.add(dir.scale(t));
//    }
//
//    /**
//     * finds the closest point to the ray's head in a list of points (assumingly on
//     * the ray)
//     *
//     * @param lst List of points on the ray
//     * @return The closest one to the ray's head
//     */
//    public Point findClosestPoint(List<Point> lst) {
//        if (lst == null)
//            return null;
//        if (lst.size() == 0)
//            return null;
//
//        // convert point list to gp list (with null in the gp.geometry)
//        return findClosestGeoPoint(lst.stream().map(p -> new GeoPoint(null, p)).toList()).point;
//
//    }
//
//    /**
//     * finds the closest geo point to the ray's head in a list of geo points
//     * (assumingly on the ray)
//     *
//     * @param lst List of geo points on the ray
//     * @return The closest one to the ray's head
//     */
//    public GeoPoint findClosestGeoPoint(List<GeoPoint> lst) {
//        if (lst == null)
//            return null;
//
//        GeoPoint closest = null;
//        double min = Double.POSITIVE_INFINITY;
//        for (GeoPoint gp : lst) {
//            double dist = p0.distance(gp.point);
//            if (dist < min) {
//                min = dist;
//                closest = gp;
//            }
//        }
//        return closest;
//    }
//}