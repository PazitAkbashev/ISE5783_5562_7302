package renderer;

import geometries.Geometries;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

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
            Color color=scene.background;
            Geometries geometries = scene.setGeometries();
            List<Point> intersectionPoints = geometries.findIntersections(ray);
            if (intersectionPoints != null) {
                Point closesPoint = ray.findClosestPoint(intersectionPoints);
                color = calcColor(closesPoint);
            }
            return color;
    }

    privet Color calcColor(Point point){return scene.setAmbientLight().getIntensity();}
}
