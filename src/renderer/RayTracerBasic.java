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

    /**
     * Calculates the transparency factor for a given intersection point, light source, incident ray, and surface normal.
     *
     * @param geopoint The intersection point on the geometry.
     * @param light The light source.
     * @param l The incident ray direction (from point to light source).
     * @param n The surface normal at the intersection point.
     * @return The transparency factor as a Double3 value.
     */
    private Double3 transparency(GeoPoint geopoint, LightSource light, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1);  // Calculate the direction from the intersection point to the light source
        Ray lightRay = new Ray(geopoint.point, lightDirection, n);  // Build a ray from the intersection point towards the light source with a small delta
        double lightDistance = light.getDistance(geopoint.point);  // Calculate the distance between the intersection point and the light source
        var intersections = scene.geometries.findGeoIntersections(lightRay);  // Find the intersections of the ray with the scene geometries
        if (intersections == null) return Double3.ONE;  // If there are no intersections, the transparency factor is 1 (fully transparent)

        Double3 ktr = new Double3(1d);  // Initialize the transparency factor as 1 (fully transparent)

        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0) {
                // If the distance between the intersection point and the light source is close to the actual light distance,
                // calculate the transparency factor based on the material's transparency coefficient
                ktr = ktr.product(gp.geometry.getMaterial().kT);  // Multiply the transparency factor with the material's transparency coefficient
                if (ktr.lowerThan(MIN_CALC_COLOR_K)) return Double3.ZERO;  // If the transparency factor falls below a minimum threshold, return zero (fully opaque)
            }
        }

        return ktr;  // Return the calculated transparency factor
    }


    /**
     * Calculates the color at the given intersection point for the specified ray.
     *
     * @param gp The intersection point.
     * @param ray The ray.
     * @return The calculated color.
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }

    /**
     * Recursive function for calculating the color at the given intersection point for the specified ray.
     *
     * @param intersection The intersection point.
     * @param ray The ray.
     * @param level The current recursion level.
     * @param k The accumulated attenuation factor.
     * @return The calculated color.
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(intersection, ray);  // Calculate the local effects at the intersection point
        return 1 == level ?
                color :
                color.add(calcGlobalEffects(intersection, ray, level, k));  // Add the global effects recursively if the level is not 1
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
     * Calculates the local effects of the light at the intersection point.
     *
     * @param gp The intersection point.
     * @param ray The ray from the camera.
     * @return The color of the local effects.
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Point intersectionPoint = gp.point;
        Color color = gp.geometry.getEmission();  // Start with the emission color of the geometry
        Vector v = ray.getDir();  // Direction of the ray from the camera
        Vector n = gp.geometry.getNormal(intersectionPoint);  // Normal vector at the intersection point
        double nv = alignZero(n.dotProduct(v));  // Dot product between the normal vector and the ray direction

        if (nv == 0)
            return color;  // If the dot product is zero, return the emission color (no lighting effect)

        Material material = gp.geometry.getMaterial();  // Material of the geometry

        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersectionPoint);  // Direction to the light source
            double nl = alignZero(n.dotProduct(l));  // Dot product between the normal vector and the light direction

            // sign(nl) == sign(nv)
            if (nl * nv > 0) {
                Color iL = lightSource.getIntensity(intersectionPoint);  // Intensity of the light source at the intersection point
                color = color.add(iL.scale(calcDiffusive(material, nl)),  // Add the diffuse component of the light
                        iL.scale(calcSpecular(material, n, l, nl, v)));  // Add the specular component of the light
            }
        }
        return color;  // Return the final color after considering all light sources
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

    /**
     * Calculates the specular component of the light reflection using the Phong model.
     *
     * @param material The material of the geometry.
     * @param n The normal vector at the intersection point.
     * @param l The direction to the light source.
     * @param nl The dot product between the normal vector and the light direction.
     * @param v The direction of the ray from the camera.
     * @return The specular component of the light reflection.
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(alignZero(l.dotProduct(n)) * 2));  // Calculate the reflected light direction
        return material.kS.scale(Math.pow(Math.max(0, alignZero(v.scale((-1)).dotProduct(r))), material.nShininess));
    }


    /**
     * Calculates the diffuse component of the light reflection using the Lambertian model.
     *
     * @param material The material of the geometry.
     * @param nl The dot product between the normal vector and the light direction.
     * @return The diffuse component of the light reflection.
     */
    private Double3 calcDiffusive(Material material, double nl) {
        return material.kD.scale(Math.abs(nl));
    }


    /**
     * Constructs a reflected ray based on the intersection point and the incoming ray.
     *
     * @param geoPoint The intersection point.
     * @param ray The incoming ray.
     * @return The reflected ray.
     */
    private Ray constructReflected(GeoPoint geoPoint, Ray ray) {
        Vector v = ray.getDir();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        double nv = alignZero(v.dotProduct(n));

        // Calculate the reflected direction: r = v - 2 * (v * n) * n
        Vector r = v.subtract(n.scale(2d * nv)).normalize();

        // Construct and return the reflected ray
        return new Ray(geoPoint.point, r);
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
    /**
     * Traces a ray and calculates the color of the closest intersection point.
     *
     * @param ray The ray to trace.
     * @return The color of the closest intersection point, or the background color if no intersection is found.
     */
    public Color traceRay(Ray ray) {
        GeoPoint closestGeoPoint = findClosestIntersection(ray);

        // If no intersection is found, return the background color
        if (closestGeoPoint == null) {
            return scene.background;
        }

        // Calculate the color at the closest intersection point
        return calcColor(closestGeoPoint, ray);
    }

}
