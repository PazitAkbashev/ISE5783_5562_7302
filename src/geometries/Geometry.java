package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * @author Pazit and Leah
 */
public abstract class Geometry extends Intersectable {

    protected Color emission = Color.BLACK;
    private Material material = new Material();

    /**
     * Calculate the normal vector by the point.
     * @param point the point to calculate there the normal
     * @return normal vector from the location of the point on the surface of the geometry
     */
    abstract public Vector getNormal(Point point);

    public Color getEmission() {
        return emission;
    }

    /**
     * as builder pattern
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    public Material getMaterial() {
        return material;
    }

    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

}
