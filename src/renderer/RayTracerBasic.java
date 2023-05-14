package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * RayTracerBasic class is the basic class for ray tracing in the scene
 * It is responsible for the calculation of the color of the pixel
 * this class extend from RayTracerBase class.
 *
 * @author Pazit and Leah
 */
public class RayTracerBasic extends RayTracerBase{

        /**
        * constructor for RayTracerBasic class
        * @param scene the scene we want to render
        */
        public RayTracerBasic(Scene scene) {
            super(scene);
        }

    @Override
    public Color traceRay(Ray ray) {
        return null;
    }
}
