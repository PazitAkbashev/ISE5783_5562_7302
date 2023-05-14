package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * RayTracerBasic class is the basic class for ray tracing in the scene
 * It is responsible for the calculation of the color of the pixel
 * It is an abstract class that extended by RayTracerBasic class
 *
 * @author Pazit and Leah
 */
abstract public class RayTracerBase {
    protected Scene scene;

    /**
     * constructor for RayTracerBasic class
     * @param scene the scene we want to render
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * traceRay function is the basic function for ray tracing in the scene
     * @param ray the ray we want to trace
     * @return the color of the pixel
     */
    abstract public Color traceRay(Ray ray);
}
