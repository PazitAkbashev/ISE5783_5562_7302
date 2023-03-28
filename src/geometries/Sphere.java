package geometries;
import primitives.*;

/**
 * this class represent a sphere by center point and radius
 * @author  Pazit and Leah
 */
public class Sphere extends RadialGeometry{

    final Point center;

    public Sphere(Point center, double radius) {
        super(radius);
        this.center = center;
    }

    @Override
    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }
}
