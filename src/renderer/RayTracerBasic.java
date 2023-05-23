package renderer;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

import static java.awt.Color.yellow;

/**
 * RayTracerBasic class is the basic class for ray tracing in the scene
 * It is responsible for the calculation of the color of the pixel
 * this class extend from RayTracerBase class.
 *
 * @author Pazit and Leah
 */
public class RayTracerBasic extends RayTracerBase {

    /**
     * constructor for RayTracerBasic class
     *
     * @param scene the scene we want to render
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {

        /**if not found intersections points return the background color*/
        Color color = scene.background;

        Geometries geometries = scene.geometries;

        List<Point> intersectionPoints = geometries.findIntersections(ray);
        if (intersectionPoints != null) {
            Point closesPoint = ray.findClosestPoint(intersectionPoints);
            color = calcColor(closesPoint);
        }
        return color;
    }

    //--------access to ambientLight by builder--------

    /**
     * in this stage the method returning the color of the ambient light of the scene
     * the color gets the numbers like this because a mistake in stage 6 and 5 together
     */
    //private Color
    private Color calcColor(Point point) {
        Scene scene = new Scene.SceneBuilder("scene1").build();
        Color color = new Color(255, 191, 191);
        return color;
    }
}
