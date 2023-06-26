package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * An interface called  Geometry for geometric shapes
 *
 * @author Pazit and Leah - 26.06.23
 */
public abstract class Geometry extends Intersectable {

    // The color of the geometry - not private!
    private Color emission = Color.BLACK;

    // The material of the geometry - not private!
    private Material material = new Material();

    /**
     * Calculate the normal vector by the point.
     *
     * @param point the point to calculate there the normal
     * @return normal vector from the location of the point on the surface of the geometry
     */
    abstract public Vector getNormal(Point point);

    /**
     * return the emission color of the geometry
     *
     * @return the emission color
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * setter for the emission color - defined as private
     *
     * @param emission the emission color to set
     * @return the geometry itself
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * getter for the material of the geometry
     *
     * @return the material of the geometry
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * setter for the material of the geometry
     *
     * @param material the material to set
     * @return the geometry itself
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }
}
