package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * RayTracerBasic class is the basic class for ray tracing in the scene
 * It is responsible for the calculation of the color of the pixel
 * this class extend from RayTracerBase class.
 *
 * @author Pazit and Leah
 */
public class RayTracerBasic extends RayTracerBase {

    /**
     *
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;

    private static final double DELTA = 0.1;

    /**
     * @param gp the intersection point
     * @param l  the light source
     * @param n  the normal
     * @return true if the point is unshaded, else false
     */
    private boolean unshaded(GeoPoint gp, Vector l, Vector n, LightSource lightSource) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector epsVector = n.scale(DELTA);
        Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        return intersections.isEmpty(); //TODO
    }

//        Vector lightDirection = l.scale(-1); // from point to light source
//        double nl = n.dotProduct(lightDirection);
//
//        Vector delta = n.scale(nl > 0 ? DELTA : -DELTA);
//        Point pointRay = gp.point.add(delta);
//        Ray shadowRay = new Ray(pointRay, lightDirection);
//
//        double maxDistance = lightSource.getDistance(gp.point);
//        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(shadowRay, maxDistance);
//
//        if (intersections == null) {
//            return true;
//        }
//
//        for (GeoPoint item : intersections) {
//            if (item.geometry.) {
//                return true;//TODO
//            }
//        }
//
//        return false;
//    }


    /**
     * constructor for RayTracerBasic class
     *
     * @param scene the scene we want to render
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * in this stage the method returning the color of the ambient light of the scene
     * the color gets the numbers like this because a mistake in stage 6 and 5 together
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        //if the point is not shaded
//        if (unshaded(gp, scene.lights.get(0).getL(gp.point), gp.geometry.getNormal(gp.point), scene.lights.get(0))) {
//            Color color = scene.ambientLight.getIntensity().add(calcLocalEffects(gp, ray));
//            return color;
//        }
//            //else- if shaded:
//            return Color.BLACK;
        return Color.BLACK;
    }

    /**
     * calcLocalEffects function is the function that calculate the local effects of the light
     *
     * @param gp  the intersection point
     * @param ray the ray from the camera
     * @return the color of the local effects
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Point intersectionPoint = gp.point;
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(intersectionPoint);
        double nv = alignZero(n.dotProduct(v));

        if (nv == 0)
            return color;

        Material material = gp.geometry.getMaterial();

        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersectionPoint);
            double nl = alignZero(n.dotProduct(l));

            // sign(nl) == sign(nv)
            if (nl * nv > 0) {
                Color iL = lightSource.getIntensity(intersectionPoint);
                color = color.add(iL.scale(calcDiffusive(material, nl)),
                        iL.scale(calcSpecular(material, n, l, nl, v)));
            }
        }

        return color;
    }

    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(alignZero(l.dotProduct(n)) * 2));
        return material.kS.scale(Math.pow(Math.max(0,
                        alignZero(v.scale((-1)).dotProduct(r))),
                material.nShininess));
    }

    private Double3 calcDiffusive(Material material, double nl)
    {
        return material.kD.scale(Math.abs(nl));
    }

    @Override
    public Color traceRay(Ray ray) {
        GeoPoint clossestGeoPoint = findClosestIntersection(ray);
        if (clossestGeoPoint == null)
            return scene.background;
        return calcColor(clossestGeoPoint, ray);
    }

    private Ray constructReflected(GeoPoint geoPoint, Ray ray) {
        Vector v = ray.getDir();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        double nv = alignZero(v.dotProduct(n));
        // r = v - 2*(v * n) * n
        Vector r = v.subtract(n.scale(2d * nv)).normalize();

        return new Ray(geoPoint.point, r); //needs to add the normal?
    }


//    private Ray constructRefracted(GeoPoint geoPoint, Ray inRay) {
//        //return new Ray(geoPoint.point, inRay.getDir(), geoPoint.geometry.getNormal(geoPoint.point));
//        return new Ray()
//    }//TODO

    private GeoPoint findClosestIntersection(Ray ray) {
        if (ray == null) {
            return null;
        }
        List<GeoPoint> points = scene.geometries.findGeoIntersections(ray);
        return ray.findClosestGeoPoint(points);
    }
}
