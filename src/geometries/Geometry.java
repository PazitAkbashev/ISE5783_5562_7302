package geometries;
import primitives.*;
/**
 * Abstract class for all the geometries shapes
 * @author Pazit and Leah
 */
public interface Geometry {
    /**
     * getter of material that getting a point
     * @return material
     */
    public Vector getNormal(Point point);
}
