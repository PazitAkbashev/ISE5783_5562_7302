package primitives;
import primitives.Double3;
import java.util.Objects;

/**
 * this is a basic point for RayTracing project in 3D
 * @author Pazit and Leah
 */
public class Point {
    protected Double3 point3d;

    /**
     * constructor
     * @param point3d value of point
     */
    public Point(Double3 point3d) {
        this.point3d = point3d;
    }

    /**
     * constructor
     * @param d1 value of x
     * @param d2 value of y
     * @param d3 value of z
     */
     public Point (double d1, double d2, double d3){
        this.point3d = new Double3(d1, d2, d3);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return point3d.equals(point.point3d);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point3d);
    }

    @Override
    public String toString() {
        return point3d.toString();
    }
}
