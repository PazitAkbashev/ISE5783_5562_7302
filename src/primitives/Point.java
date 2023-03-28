package primitives;
import java.util.Objects;

/**
 * this is a basic point for RayTracing project in 3D
 * @author Pazit and Leah
 */
public class Point {
    final Double3 xyz;

    /**
     * constructor
     * @param xyz value of point
     */
    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * constructor
     * @param d1 value of x
     * @param d2 value of y
     * @param d3 value of z
     */
     public Point (double d1, double d2, double d3){
        this.xyz = new Double3(d1, d2, d3);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return xyz.equals(point.xyz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xyz);
    }

    @Override
    public String toString() {
        return xyz.toString();
    }

    /**
     *Subtraction vector and dot
     * @param point to subtract
     * @return Vector
     */
    public Vector subtract(Point point) {
        return new Vector(xyz.subtract(point.xyz));
    }
    /**
     *Connecting vector and dot
     * @param vec to adding
     * @return point
     */
    public Point add(Vector vec) {
        return new Point(this.xyz.add(vec.xyz));
    }
    /**
     *Distance squared
     * @param point
     * @return Distance squared
     */
    public double distanceSquared(Point point) {
        Double3 result =this.xyz.subtract(point.xyz);
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
