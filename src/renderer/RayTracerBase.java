package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * RayTracerBasic class is the basic class for ray tracing in the scene
 * It is responsible for the calculation of the color of the pixel
 * It is an abstract class that extended by RayTracerBasic class
 *
 * @author Pazit and Leah - 26.06.23
 */
public abstract class RayTracerBase {
    //the scene we want to trace
    protected Scene scene;

    /**
     * constructor for RayTracerBasic class
     *
     * @param scene the scene we want to
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * traceRay function is the function that calculate the color of the closest point to the camera
     *
     * @param ray the ray we want to find the closest intersection with
     * @return the color of the closest point to the camera which on the ray
     */
    public abstract Color traceRay(Ray ray);
}
