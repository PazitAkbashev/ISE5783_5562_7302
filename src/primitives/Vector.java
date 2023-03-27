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
        return new Vector(point3d.reduce(length()));
    }
    /**
     * add two vectors
     * @param vec the other vector to add
     * @return new Vector from (x1 + x2, y1 + y2, z1 + z2)
     */
    public Vector add(Vector vec) {
        return new Vector(point3d.add(vec.point3d));
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
        return new Vector(point3d.d1*num, point3d.d2*num, point3d.d3*num);
    }
    /**
     * dot product of tow vectors (x1, y1, z1), (x2, y2, z2)
     * @param vec (x2, y2, z2)
     * @return x1*x2 + y1*y2 + z1*z2
     */
    public double dotProduct(Vector vec){
        return (vec.point3d.d1 * point3d.d1)+(vec.point3d.d2 * point3d.d2)+(vec.point3d.d3 * point3d.d3);
    }
    /**
     * cross product of two vectors (x1, y1, z1), (x2, y2, z2)
     * @param vec (x2, y2, z2)
     * @return new Vector from (x1, y1, z1)X(x2, y2, z2) =
     * = (y1*z2 -z1*y2, z1*x2 - x1*z2, x1*y2 - y1*x2)
     */
    public Vector crossProduct(Vector vec){
        return new Vector(point3d.d2*vec.point3d.d3-point3d.d3*vec.point3d.d2, point3d.d3*vec.point3d.d1- point3d.d1*vec.point3d.d3, point3d.d1*vec.point3d.d2- point3d.d2*vec.point3d.d1);
    }
    /**
     * the squared length of the vector (x, y, z)
     * @return x^2 + y^2 + z^2
     */
    public double lengthSquared(){
        return (point3d.d1 * point3d.d1)+(point3d.d2 * point3d.d2)+(point3d.d3 * point3d.d3);
    }
    /**
     * the length of the vector (x, y, z)
     * @return sqrt(x^2 + y^2 + z^2)
     */
    public double length(){
        return Math.sqrt(this.lengthSquared());
    }
}
