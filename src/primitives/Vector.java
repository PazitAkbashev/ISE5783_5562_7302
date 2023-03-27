package primitives;

/**
 * this class represent a Vector of 3D
 * @author Pazit and Leah
 */
public class Vector extends Point {
    /**
     * @param point3d is a 3d point
     */
    public Vector(Double3 point3d) {
        super(point3d);
    }

    /**
     * @param d1 value of x
     * @param d2 value of y
     * @param d3 value of z
     */
    public Vector(double d1, double d2, double d3) {
        super(d1, d2, d3);
    }

    /**
     *the normal of this vector of (x, y, z)
     * @return (x, y, z) / |(x, y, z)| = (x, y, z) / sqrt(x^2 + y^2 + z^2)
     */
    public Vector normalize(){
        return new Vector(point3d.reduce(length()));
    }
}
