package primitives;

import java.util.Objects;

import static primitives.Util.isZero;

/**
 * A fundamental object in geometry with direction and size,
 * according to the approach of linear algebra, is like a point,
 * defined by the end point.
 *
 * @author Pazit and Leah - 26.06.23
 */
public class Vector extends Point {
    /**
     * A constructor that accepts three numbers of type double3
     *
     * @param x value of x
     * @param y value of y
     * @param z value of z
     */
    public Vector(double x, double y, double z) {
        //initialize the starting point of the ray
        this(new Double3(x, y, z));
    }

    /**
     * A constructor that accepts an object of type Double3 permission
     *
     * @param double3 value of point
     */
    public Vector(Double3 double3) {
        super(double3);
        if (Double3.ZERO.equals(double3))
            throw new IllegalArgumentException("vector 0 (c-tor vector class)");
    }

    /**
     * A normalization operation that returns a new normalized vector
     * a unit vector in the same direction as the original vector
     *
     * @return new normelized vector
     */
    public Vector normalize() {
        return new Vector(xyz.reduce(length()));
    }

    /**
     * add two vectors
     *
     * @param vec the other vector to add
     * @return new Vector from (x1 + x2, y1 + y2, z1 + z2)
     */
    public Vector add(Vector vec) {
        return new Vector(xyz.add(vec.xyz));
    }

    /**
     * multiply this vector with a scalar
     *
     * @param num the scalar
     * @return new Vector with the value of (scalar * x, scalar * y, scalar * z)
     * @throws IllegalArgumentException if scalar = 0
     */
    public Vector scale(double num) {
//        if (isZero(num))
//            throw new IllegalArgumentException("vector 0 - Vector class - scale");
        return new Vector(xyz.scale(num));
    }


    /**
     * dot product of tow vectors (x1, y1, z1), (x2, y2, z2)
     *
     * @param vec (x2, y2, z2)
     * @return x1*x2 + y1*y2 + z1*z2
     */
    public double dotProduct(Vector vec) {
        //d1 = x1*x2
        double d1 = (vec.xyz.d1 * xyz.d1);

        //d2 = y1*y2
        double d2 = (vec.xyz.d2 * xyz.d2);

        //d3 = z1*z2
        double d3 = (vec.xyz.d3 * xyz.d3);
        return d1 + d2 + d3;
    }

    /**
     * cross product of two vectors (x1, y1, z1), (x2, y2, z2)
     *
     * @param vec (x2, y2, z2)
     * @return new Vector from (x1, y1, z1)X(x2, y2, z2) =
     * = (y1*z2 -z1*y2, z1*x2 - x1*z2, x1*y2 - y1*x2)
     */
    public Vector crossProduct(Vector vec) {
        //xx = y1*z2 -z1*y2
        double xx = xyz.d2 * vec.xyz.d3 - xyz.d3 * vec.xyz.d2;

        //yy = z1*x2 - x1*z2
        double yy = xyz.d3 * vec.xyz.d1 - xyz.d1 * vec.xyz.d3;

        //zz = x1*y2 - y1*x2
        double zz = xyz.d1 * vec.xyz.d2 - xyz.d2 * vec.xyz.d1;

        return new Vector(xx, yy, zz);
    }

    /**
     * the squared length of the vector (x, y, z)
     *
     * @return x^2 + y^2 + z^2
     */
    public double lengthSquared() {
        //d1 = x^2
        double d1 = (xyz.d1 * xyz.d1);

        //d2 = y^2
        double d2 = (xyz.d2 * xyz.d2);

        //d3 = z^2
        double d3 = (xyz.d3 * xyz.d3);
        return d1 + d2 + d3;
    }

    /**
     * the length of the vector (x, y, z)
     *
     * @return sqrt(x ^ 2 + y ^ 2 + z ^ 2)
     */
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    @Override
    public boolean equals(Object o) {
        // add comment
        if (this ==o)
            return true;
        // If the object is null or of a different class, they are not equal
        if (o == null || getClass() != o.getClass())
            return false;
        // Cast the object to Vector type for comparison
        Vector vector = (Vector) o;
        // Compare the xyz field of the vectors for equality using the Objects.equals() method
        return Objects.equals(xyz, vector.xyz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xyz);
    }

}