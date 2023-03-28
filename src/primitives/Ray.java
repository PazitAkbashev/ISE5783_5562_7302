package primitives;

/**
 * this class represent a ray by starting point and direction
 * @author Pazit and Leah
 */
public class Ray{
    private Point p0;
    private Vector dir;

    /**
     * A constructor that accepts a point and a vector
     * @param p0 is a point x,y,z
     * @param dir The direction of the vector
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }
    /**
     * getter of point
     * @return p0-Vector's starting point
     */
    public Point getP0() {
        return p0;
    }
    /**
     * getter of vector
     * @return dir-The direction of the vector
     */
    public Vector getDir() {
        return dir;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray)) return false;
        Ray ray = (Ray) o;
        return getP0().equals(ray.getP0()) && getDir().equals(ray.getDir());
    }
    @Override
    public String toString() {
        //todo
        return p0.toString()+ dir.toString();
    }
}
