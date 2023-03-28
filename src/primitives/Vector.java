package primitives;

/**
 * this class represent a Vector of 3D
 * @author Pazit and Leah
 */
public class Vector extends Point {
     /**
     * constructor
     * @param x value of x
     * @param y value of y
     * @param z value of z
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if(Double3.ZERO.equals(new Double3(x,y,z)))
            throw new IllegalArgumentException("vector 0");
    }
    /**
     * constructor
     * @param double3 value of point
     */
    public Vector(Double3 double3){
        super(double3);
        if(Double3.ZERO.equals(double3))
            throw new IllegalArgumentException("vector 0");
    }
    public Vector normalize(){
        return new Vector(xyz.reduce(length()));
    }
    /**
     * add two vectors
     * @param vec the other vector to add
     * @return new Vector from (x1 + x2, y1 + y2, z1 + z2)
     */
    public Vector add(Vector vec) {
        return new Vector(xyz.add(vec.xyz));
    }
    /**
     * multiply this vector with a scalar
     * @param num the scalar
     * @return new Vector with the value of (scalar * x, scalar * y, scalar * z)
     * @throws IllegalArgumentException if scalar = 0
     */
    public Vector scale (double num){
        if (num==0)
            throw new IllegalArgumentException("vector 0");
        return new Vector(xyz.d1*num, xyz.d2*num, xyz.d3*num);
    }
    /**
     * dot product of tow vectors (x1, y1, z1), (x2, y2, z2)
     * @param vec (x2, y2, z2)
     * @return x1*x2 + y1*y2 + z1*z2
     */
    public double dotProduct(Vector vec){
        return (vec.xyz.d1 * xyz.d1)+(vec.xyz.d2 * xyz.d2)+(vec.xyz.d3 * xyz.d3);
    }
    /**
     * cross product of two vectors (x1, y1, z1), (x2, y2, z2)
     * @param vec (x2, y2, z2)
     * @return new Vector from (x1, y1, z1)X(x2, y2, z2) =
     * = (y1*z2 -z1*y2, z1*x2 - x1*z2, x1*y2 - y1*x2)
     */
    public Vector crossProduct(Vector vec){
        return new Vector(xyz.d2*vec.xyz.d3- xyz.d3*vec.xyz.d2, xyz.d3*vec.xyz.d1- xyz.d1*vec.xyz.d3, xyz.d1*vec.xyz.d2- xyz.d2*vec.xyz.d1);
    }
    /**
     * the squared length of the vector (x, y, z)
     * @return x^2 + y^2 + z^2
     */
    public double lengthSquared(){
        return (xyz.d1 * xyz.d1)+(xyz.d2 * xyz.d2)+(xyz.d3 * xyz.d3);
    }
    /**
     * the length of the vector (x, y, z)
     * @return sqrt(x^2 + y^2 + z^2)
     */
    public double length(){
        return Math.sqrt(this.lengthSquared());
    }
}
