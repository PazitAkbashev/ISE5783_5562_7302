package geometries;

import primitives.*;

/**
 * Interface for Geometry.
 *
 * @author Pazit and Leah
 */
public interface Geometry extends Intersectable {
    /**
     * Calculate the normal vector by the point.
     *
     * @param point the point to calculate there the normal
     * @return normal vector from the location of the point on the surface of the geometry
     */
    public Vector getNormal(Point point);
}
