package geometries;

/**
 * Polygon class RadialGeometry is an abstract class that is implemented by all classes that contain a radius
 *
 * @author Pazit and Lea
 */
public abstract class RadialGeometry implements Geometry {
    final double radius;

    /**
     * constructor to initialize radius
     *
     * @param radius
     */
    protected RadialGeometry(double radius) {
        this.radius = radius;
    }
}
