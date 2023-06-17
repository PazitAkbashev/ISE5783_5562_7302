package primitives;

import java.util.Objects;

import static primitives.Util.isZero;

/**
 * A fundamental object in geometry with direction and size,
 * according to the approach of linear algebra, is like a point, defined by the end point.
 *
 * @author Pazit and Leah
 */
public class Vector extends Point {
    /**
     * A constructor that accepts three numbers of type double3
     *
     * @param x value of x
     * @param y value of y
     * @param z value of z
     */
//    public Vector(double x, double y, double z) {
//        this(new Double3(x, y, z));
//    }
    public Vector(double x,double y,double z)//shinitiiiiiiii
    {
        super(x,y,z);
        if(Double3.ZERO.equals(new Double3(x,y,z)))
            throw new IllegalArgumentException("vector 0");
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
//    public Vector scale(double num) {
//        if (isZero(num))
//            throw new IllegalArgumentException("vector 0 - Vactor class - scale");
//        return new Vector(xyz.scale(num));
//    }
    public Vector scale (double num){//shinitiiiiiiiiiiii
        if(num==0)
            throw new IllegalArgumentException("vector 0");
        return new Vector(xyz.d1*num,xyz.d2*num, xyz.d3*num);
    }

    /**
     * dot product of tow vectors (x1, y1, z1), (x2, y2, z2)
     *
     * @param vec (x2, y2, z2)
     * @return x1*x2 + y1*y2 + z1*z2
     */
    public double dotProduct(Vector vec) {
        double d1 = (vec.xyz.d1 * xyz.d1);
        double d2 = (vec.xyz.d2 * xyz.d2);
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
        double xx = xyz.d2 * vec.xyz.d3 - xyz.d3 * vec.xyz.d2;
        double yy = xyz.d3 * vec.xyz.d1 - xyz.d1 * vec.xyz.d3;
        double zz = xyz.d1 * vec.xyz.d2 - xyz.d2 * vec.xyz.d1;

        return new Vector(xx, yy, zz);
    }

    /**
     * the squared length of the vector (x, y, z)
     *
     * @return x^2 + y^2 + z^2
     */
    public double lengthSquared() {
        double d1 = (xyz.d1 * xyz.d1);
        double d2 = (xyz.d2 * xyz.d2);
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

//
//public class Vector extends Point {
//
//    /**
//     * A ctor to initialize a Vector with a Double3
//     *
//     * @param xyz a Double3 that holds 3 coordinates
//     */
//    Vector(Double3 xyz) {
//        super(xyz);
//        if (xyz.equals(Double3.ZERO))
//            throw new IllegalArgumentException("ERROR: Vector ctor can't get Double3 ZERO"); // an exception protects
//        // against vector ZERO
//
//    }
//
//    /**
//     * A ctor to initialize a Vector with 3 Doubles. Uses the other ctor.
//     *
//     * @param x First coordinate
//     * @param y Second coordinate
//     * @param z Third coordinate
//     */
//    public Vector(double x, double y, double z) {
//        this(new Double3(x, y, z));
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj instanceof Vector other)
//            return super.equals(other);
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
//        return "Vector ->" + super.toString();
//    }
//
//    @Override
//    public Vector add(Vector vec) {
//        return new Vector(xyz.add(vec.xyz));
//    }
//
//    /**
//     * Multiplies each dimention with a scalar.
//     *
//     * @param sca is the scalar to multiply with
//     * @return a new vector which is scaled
//     */
//    public Vector scale(double sca) {
//        return new Vector(xyz.scale(sca));
//    }
//
//    /**
//     * Calculates the cross product of two vectors.
//     *
//     * @param vec is the vector to multiply with
//     * @return a new vector which is orthogonal to both vectors
//     */
//    public Vector crossProduct(Vector vec) {
//        return new Vector(xyz.d2 * vec.xyz.d3 - xyz.d3 * vec.xyz.d2, //
//                -(xyz.d1 * vec.xyz.d3 - xyz.d3 * vec.xyz.d1), //
//                xyz.d1 * vec.xyz.d2 - xyz.d2 * vec.xyz.d1);
//    }
//
//    /**
//     * Calculates the length of a vector, squared
//     *
//     * @return the length squared
//     */
//    public double lengthSquared() {
//        return xyz.d1 * xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3;
//    }
//
//    /**
//     * Calculates the length of a vector, uses the lengthSquared() method
//     *
//     * @return the length
//     */
//    public double length() {
//        return Math.sqrt(lengthSquared());
//    }
//
//    /**
//     * Normalize the vector
//     *
//     * @return a new vector with the same direction, with length 1
//     */
//    public Vector normalize() {
//        return new Vector(xyz.reduce(length()));
//    }
//
//    /**
//     * Calculates the dot product of 2 vectors
//     *
//     * @param vec is the vector to dot product with
//     * @return dot product, which is also the Cos(alpha) between the two
//     */
//    public double dotProduct(Vector vec) {
//        return xyz.d1 * vec.xyz.d1 + xyz.d2 * vec.xyz.d2 + xyz.d3 * vec.xyz.d3;
//    }
//
//}
