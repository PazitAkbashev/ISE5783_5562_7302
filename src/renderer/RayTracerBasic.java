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

    /**the maximum level for calcColor function (the maximum level for the recursive call)*/
    private static final int MAX_CALC_COLOR_LEVEL = 10;

    /**
     * the minimum k for calcColor function (the minimum k for the recursive call)
     */
    private static final double MIN_CALC_COLOR_K = 0.001;

    /**
     * the initial k for calcColor function (the initial k for the recursive call)
     */
    private static final Double3 INITIAL_K = new Double3(1d);

    /**
     * constructor for RayTracerBasic class
     *
     * @param scene the scene we want to render
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    private Double3 transparency(GeoPoint geopoint, LightSource light, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.point, lightDirection, n); //build ray with delta
        double lightDistance = light.getDistance(geopoint.point);
        var intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null) return Double3.ONE; //no intersections
        Double3 ktr = new Double3(1d);

        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0) {
                ktr = ktr.product(gp.geometry.getMaterial().kT); //the more transparency the less shadow
                if (ktr.lowerThan(MIN_CALC_COLOR_K)) return Double3.ZERO;
            }
        }
        return ktr;
    }

    /**helper function for calcColor function */
    private Color calcColor(GeoPoint gp, Ray ray) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }

    /* taken from instruction's slides*/
    private Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(intersection, ray);
        return 1 == level ?
                color :
                color.add(calcGlobalEffects(intersection, ray, level, k));
    }
    /* with Rivki's help*/
//    private Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
//        Color color = intersection.geometry.getEmission()
//                //calcLocalEffects with only 2 parameters? or with k either?
//                .add(calcLocalEffects(intersection, ray));
//
//        return 1 == level ? color
//                : color.add(calcGlobalEffects(intersection, ray, level, k));
//    }


    //check if correct????? from here and down
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

    /**
     * calcGlobalEffects function is the function that calculate the global effects of the light
     * taken from instruction's slides
     *
     * @param gp
     * @param ray
     * @param level
     * @param k
     * @return
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Material material = gp.geometry.getMaterial();
        //the reflection coefficient
        Double3 kr = material.kR;
        //in each recursive iteration the impact of the reflection decreases
        Double3 kkr = k.product(kr);
        //normal to the geometry
        Vector n = gp.geometry.getNormal(gp.point);
        //
        if (!kkr.lowerThan(MIN_CALC_COLOR_K)) {
            Ray reflectedRay = constructReflected(gp, ray);
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            if (reflectedPoint == null)
                return color.add(scene.background);
            color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
        }
        //
        Double3 kt = material.kT;
        Double3 kkt = k.product(kt); //in each recursive iteration the impact of the refraction decreases
        if (!kkt.lowerThan(MIN_CALC_COLOR_K)) {
            Ray refractedRay = constructReflected(gp, ray);
            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
            if (refractedPoint == null) return color.add(scene.background);
            color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
        }
        return color;
    }

    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(alignZero(l.dotProduct(n)) * 2));
        return material.kS.scale(Math.pow(Math.max(0,
                        alignZero(v.scale((-1)).dotProduct(r))),
                material.nShininess));
    }

    private Double3 calcDiffusive(Material material, double nl) {
        return material.kD.scale(Math.abs(nl));
    }

    private Ray constructReflected(GeoPoint geoPoint, Ray ray) {
        Vector v = ray.getDir();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        double nv = alignZero(v.dotProduct(n));
        // r = v - 2*(v * n) * n
        Vector r = v.subtract(n.scale(2d * nv)).normalize();

        return new Ray(geoPoint.point, r); //needs to add the normal?
    }

    /**
     * constructReflectedRay function is the function that checks if the object is reflective
     * and if it is, it calculates the reflected ray
     */

    /**
     * find the closest intersection of the ray with the objects in the scene
     * using the findGeoIntersections function from the ray class
     *
     * @param ray the ray we're finding the closest intersection to
     * @return the closest intersection point
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        if (ray == null) {
            return null;
        }

        List<GeoPoint> points = scene.geometries.findGeoIntersections(ray);
        return ray.findClosestGeoPoint(points);
    }

    /* taken from instruction's slides*/
    @Override
    public Color traceRay(Ray ray) {
        GeoPoint clossestGeoPoint = findClosestIntersection(ray);
        if (clossestGeoPoint == null)
            return scene.background;
        return calcColor(clossestGeoPoint, ray);
    }
}
