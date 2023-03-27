package primitives;
import primitives.Double3;
import java.util.Objects;

/**
 * this is a basic point for RayTracing project in 3D
 * @author Pazit and Leah
 */
public class Point {
    protected Double3 point3d;

    /**
     * constructor
     * @param point3d value of point
     */
    public Point(Double3 point3d) {
        this.point3d = point3d;
    }

    /**
     * constructor
     * @param d1 value of x
     * @param d2 value of y
     * @param d3 value of z
     */
     public Point (double d1, double d2, double d3){
        this.point3d = new Double3(d1, d2, d3);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return point3d.equals(point.point3d);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point3d);
    }

    @Override
    public String toString() {
        return point3d.toString();
    }

    /**
     *Subtraction vector and dot
     * @param point to subtract
     * @return Vector
     */
    public Vector subtract(Point point) {
        return new Vector(point3d.subtract(point.point3d));
    }
    /**
     *Connecting vector and dot
     * @param vec to adding
     * @return point
     */
    public Point add(Vector vec) {
        return new Point(this.point3d.add(vec.point3d));
    }
    /**
     *Distance squared
     * @param point
     * @return Distance squared
     */
    public double distanceSquared(Point point) {
        Double3 result =this.point3d.subtract(point.point3d);
        return (result.d1*result.d1)+(result.d2*result.d2)+(result.d3*result.d3);
    }
    /**
     *Distance
     * @param point
     * @return Distance
     */
    public double distance(Point point) {
        return Math.sqrt(this.distanceSquared(point));
    }
}
