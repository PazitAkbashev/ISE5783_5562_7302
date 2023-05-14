package primitives;

import java.util.List;

/**

 - This class represents a ray, defined by a starting point and a direction vector.

 - The Ray class has methods for getting the starting point and direction of the ray,
   and for calculating a new point along the ray given a specific distance.

 - The class also overrides the equals() method to check if two Ray objects are equal by comparing their
   starting point and direction.

 - It also provides a toString() method that returns a string representation of the ray in the form of its
   starting point and direction.

 - The class requires a Point object to define the starting point and a Vector object to define the direction.

 @author Pazit and Leah
 */
public class Ray {
    private Point p0;
    private Vector dir;

    /**
     * find the closest point to the ray from a list of points.
     * @param intersections list of points
     * @return the closest point
     */
    Point findClosestPoint(List<Point> intersections) {
        if(intersections == null){
            return null;
        }
        Point result = null;
        double distance = Double.MAX_VALUE;
        double temp = 0;

        // checking the all points intersections
        for (Point p : intersections) {
            temp = p0.distance(p);
            if (temp < distance) {
                distance = temp;
                result = p;
            }
        }
        return result;
    }

    /**
     Constructs a new Ray object with a given starting point and direction.
     @param p0 The starting point of the ray as a Point object with x, y, and z coordinates.
     @param dir The direction of the ray as a Vector object.
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }
    /**

     Returns the starting point of the ray as a Point object.
     @return The starting point of the ray.
     */
    public Point getP0() {
        return p0;
    }
    /**

     Returns the direction of the ray as a Vector object.
     @return The direction of the ray.
     */
    public Vector getDir() {
        return dir;
    }
    /**

     Returns a new Point object that is a specific distance along the ray from the starting point.
     @param t The distance from the starting point of the desired point on the ray.
     @return A new Point object at distance t along the direction of the ray.
     */
    public Point getPoint(double t) {
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
}