package primitives;

import java.util.Objects;

/**
 * this is a basic point for RayTracing project in 3D
 *
 * @author Pazit and Leah
 */
public class Point {
    /**
     * ZERO point for the coordinate system
     */
    public final static Point ZERO = new Point(0d, 0d, 0d);//shinitiii

    /*value of point*/
   protected final Double3 xyz;//hosafti protectedddddddddddd

    /**
     * A constructor that accepts an object of type Double3 permission
     *
     * @param xyz value of point
     */
    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * A constructor that accepts three double numbers for the coordinate values
     *
     * @param d1 value of x
     * @param d2 value of y
     * @param d3 value of z
     */
    public Point(double d1, double d2, double d3) {
        xyz=new Double3(d1, d2, d3);
    }

    @Override
//    public boolean equals(Object o) {
//        if (this == o)
//            return true;
//        if (o instanceof Point other)
//            return xyz.equals(other.xyz);
//        return false;
//    }
    public boolean equals(Object o) {//shinitiiiii
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
     * Vector subtraction - receives a second point in the parameter, returns a vector from the second point to the point on which the subtraction is performed
     * the action
     *
     * @param point to subtract
     * @return Vector a vector from the second point to the point on which it is performed
     * the action
     */
//    public Vector subtract(Point point) {
//        Double3 result = this.xyz.subtract(point.xyz);
//        return new Vector(result);
//    }
    public Vector subtract(Point point){//shinitiiiiii
        return new Vector(xyz.subtract(point.xyz));
    }

    /**
     * Adding a vector to a point - returns a new point
     *
     * @param vector to adding
     * @return point new point
     */
//    public Point add(Vector vec) {
//        Double3 result = this.xyz.add(vec.xyz);
//        return new Point(result);
//    }
    public Point add(Vector vector) {//shinitiiii
        return new Point(this.xyz.add(vector.xyz));
    }

    /**
     * Distance squared-The distance between two points
     *
     * @param point the point to calculate the distance from
     * @return Distance squared
     */
    public double distanceSquared(Point point) {
        Double3 result = this.xyz.subtract(point.xyz);
        return (result.d1 * result.d1) + (result.d2 * result.d2) + (result.d3 * result.d3);
    }

    /**
     * Distance-Distance between 2 points
     *
     * @param point the point to calculate the distance from
     * @return Distance the distance between those 2 points
     */
    public double distance(Point point) {
        return Math.sqrt(this.distanceSquared(point));
    }
}
