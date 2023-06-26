package geometries;

//checked

/**
 * Polygon class RadialGeometry is an abstract class implemented by
 * all classes that contain a radius
 *
 * @author Pazit and Lea - 26.06.23
 */
public abstract class RadialGeometry extends Geometry {

    //radius of the geometry
    final double radius;

    /**
     * constructor to initialize radius
     *
     * @param radius - the radius of the geometry
     */
    protected RadialGeometry(double radius) {
        this.radius = radius;
    }
}
