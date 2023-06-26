package primitives;

/**
 * this is a basic point for RayTracing project in 3D
 *
 * @author Pazit and Leah - 26.06.23
 */
public class Point {
    //ZERO point for the coordinate system
    public static final Point ZERO = new Point(Double3.ZERO);

    //value of point
   protected final Double3 xyz;

    /**
     * A constructor that accepts an object of type Double3 permission
     *
     * @param xyz - value of point
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

    /**
     * getter for the x value of point
     *
     * @return x value of point
     */
    public double getX() {
        return (xyz.d1);
    }

    /**
     * getter for the y value of point
     *
     * @return y value of point
     */
    public double getY() {
        return (xyz.d2);
    }

    /**
     * getter for the z value of point
     *
     * @return z value of point
     */
    public double getZ() {
        return (xyz.d3);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return xyz.equals(point.xyz);
    }

    @Override
    public int hashCode() {
        return xyz.hashCode();
    }

    @Override
    public String toString() {
        return "Point " + xyz;
    }

    /**
     * Vector subtraction - receives a second point in the parameter,
     * returns a vector from the second point to the point
     * on which the subtraction is performed the action
     *
     * @param point - is the point to subtract
     * @return Vector - a vector from the second point to the
     * point on which it is performed the action
     */
    public Vector subtract(Point point){
        return new Vector(xyz.subtract(point.xyz));
    }

    /**
     * Adding a vector to a point - returns a new point
     *
     * @param vector to adding
     * @return point new point
     */
    public Point add(Vector vector) {
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

        //m1 = d1^2
        double m1 = result.d1 * result.d1;

        //m2 = d2^2
        double m2 = result.d2 * result.d2;

        //m3 = d3^2
        double m3 = result.d3 * result.d3;

        return (m1) + (m2) + (m3);
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