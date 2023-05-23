package geometries;

import primitives.*;

import java.util.List;

/**
 *
 *
 * @author Pazit and Leah
 */
public abstract class Geometry extends Intersectable {

    private Material material;

    /**
     * Calculate the normal vector by the point.
     *
     * @param point the point to calculate there the normal
     * @return normal vector from the location of the point on the surface of the geometry
     */
    abstract public Vector getNormal(Point point);

    protected Color emission = Color.BLACK;

    public Color getEmission() {
        return emission;
    }

    /** as builder pattern*/
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }
}
