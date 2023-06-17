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
//public class Point {  //for checking only.from Ynon
//    /** a 3d tuple of type double */
//    final Double3 xyz;
//    /** The zero point (0, 0, 0) */
//    public final static Point ZERO = new Point(Double3.ZERO);
//
//    /**
//     * A ctor to initialize the coordinates of the Point
//     *
//     * @param xyz A triad of Double to represent the coordinates
//     */
//    public Point(Double3 xyz) {
//        this.xyz = xyz;
//    }
//
//    /**
//     * A ctor to initialize a point with 3 Doubles
//     *
//     * @param x First coordinate
//     * @param y Second coordinate
//     * @param z Third coordinate
//     */
//    public Point(double x, double y, double z) {
//        this(new Double3(x, y, z));
//    }
//
//    /**
//     * meant for 2 points subtraction tests access
//     *
//     * @return the x field from the xyz Double3
//     */
//    public double getX() {
//        return xyz.d1;
//    }
//
//    /**
//     * Getter for
//     *
//     * @return the xyz:Double3
//     */
//    public Double3 getXyz() {
//        return xyz;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj instanceof Point other)
//            return xyz.equals(other.xyz);
//        return false;
//    }
//
//    @Override
//    public int hashCode() {
//        return xyz.hashCode();
//    }
//
//    @Override
//    public String toString() {
//        return "Point{" + xyz + "}";
//    }
//
//    /**
//     * Adds a Point with a Vector, or 2 Points
//     *
//     * @param vec is a Vector or a Point
//     * @return a Point
//     */
//    public Point add(Vector vec) {
//        return new Point(xyz.add(vec.xyz));
//    }
//
//    /**
//     * Subtract a Point from a Vector
//     *
//     * @param p is a Point
//     * @return a Vector
//     */
//    public Vector subtract(Point p) {
//        return new Vector(xyz.subtract(p.xyz));
//    }
//
//    /**
//     * Calculate the distance between 2 Points
//     *
//     * @param p is a Point
//     * @return the distance
//     */
//    public double distance(Point p) {
//        return Math.sqrt(distanceSquared(p));
//    }
//
//    /**
//     * Calculate the distance between 2 Points, squared
//     *
//     * @param p is a Point
//     * @return the distance squared
//     */
//    public double distanceSquared(Point p) {
//        double dx = xyz.d1 - p.xyz.d1;
//        double dy = xyz.d2 - p.xyz.d2;
//        double dz = xyz.d3 - p.xyz.d3;
//        return dx * dx + dy * dy + dz * dz;
//    }
//}