package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

///**
// * RayTracerBasic class is the basic class for ray tracing in the scene
// * It is responsible for the calculation of the color of the pixel
// * this class extend from RayTracerBase class.
// *
// * @author Pazit and Leah
// */
//public class RayTracerBasic extends RayTracerBase {
//
//    /*the maximum level for calcColor function (the maximum level for the recursive call)*/
//    private static final int MAX_CALC_COLOR_LEVEL = 10;
//
//    /*the minimum k for calcColor function (the minimum k for the recursive call)*/
//    private static final double MIN_CALC_COLOR_K = 0.001;
//
//    /*the initial k for calcColor function (the initial k for the recursive call)*/
//    private static final Double3 INITIAL_K = new Double3(1d);
//
//    /**
//     * constructor for RayTracerBasic class
//     *
//     * @param scene the scene we want to render
//     */
//    public RayTracerBasic(Scene scene) {
//        super(scene);
//    }
//
//    /**
//     * Calculates the transparency factor for a given intersection point, light source, incident ray, and surface normal.
//     *
//     * @param geopoint The intersection point on the geometry.
//     * @param light    The light source.
//     * @param l        The incident ray direction (from point to light source).
//     * @param n        The surface normal at the intersection point.
//     * @return The transparency factor as a Double3 value.
//     */
//    private Double3 transparency(GeoPoint geopoint, LightSource light, Vector l, Vector n) {
//        // Calculate the direction from the intersection point to the light source
//        Vector lightDirection = l.scale(-1);
//        // Build a ray from the intersection point towards the light source with a small delta
//        Ray lightRay = new Ray(geopoint.point, lightDirection, n);
//        // Calculate the distance between the intersection point and the light source
//        double lightDistance = light.getDistance(geopoint.point);
//        // Find the intersections of the ray with the scene geometries
//        var intersections = scene.geometries.findGeoIntersections(lightRay);
//        // If there are no intersections, the transparency factor is 1 (fully transparent)
//        if (intersections == null) return Double3.ONE;
//
//        // Initialize the transparency factor as 1 (fully transparent)
//        Double3 ktr = new Double3(1d);
//
//        for (GeoPoint gp : intersections) {
//            // If the distance between the intersection point and the light source is close to the actual light distance,
//            // calculate the transparency factor based on the material's transparency coefficient
//            if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0) {
//                // Multiply the transparency factor with the material's transparency coefficient
//                ktr = ktr.product(gp.geometry.getMaterial().kT);
//                // If the transparency factor falls below a minimum threshold, return zero (fully opaque)
//                if (ktr.lowerThan(MIN_CALC_COLOR_K)) return Double3.ZERO;
//            }
//        }
//
//        // Return the calculated transparency factor
//        return ktr;
//    }
//
//
//    /**
//     * Calculates the color at the given intersection point for the specified ray.
//     *
//     * @param gp  The intersection point.
//     * @param ray The ray.
//     * @return The calculated color.
//     */
//    private Color calcColor(GeoPoint gp, Ray ray) {
//        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
//                .add(scene.getAmbientLight().getIntensity());
//    }
//
//    /**
//     * Recursive function for calculating the color at the given intersection point for the specified ray.
//     *
//     * @param intersection The intersection point.
//     * @param ray          The ray.
//     * @param level        The current recursion level.
//     * @param k            The accumulated attenuation factor.
//     * @return The calculated color.
//     */
//    private Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
//        // Calculate the local effects at the intersection point
//        Color color = calcLocalEffects(intersection, ray);
//        return 1 == level ?
//                color :
//                // Add the global effects recursively if the level is not 1
//                color.add(calcGlobalEffects(intersection, ray, level, k));
//    }
//
//    /* with Rivki's help*/
////    private Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
////        Color color = intersection.geometry.getEmission()
////                //calcLocalEffects with only 2 parameters? or with k either?
////                .add(calcLocalEffects(intersection, ray));
////
////        return 1 == level ? color
////                : color.add(calcGlobalEffects(intersection, ray, level, k));
////    }
//
//    /**
//     * Calculates the local effects of the light at the intersection point.
//     *
//     * @param gp  The intersection point.
//     * @param ray The ray from the camera.
//     * @return The color of the local effects.
//     */
//    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
//        Point intersectionPoint = gp.point;
//        // Start with the emission color of the geometry
//        Color color = gp.geometry.getEmission();
//        // Direction of the ray from the camera
//        Vector v = ray.getDir();
//        // Normal vector at the intersection point
//        Vector n = gp.geometry.getNormal(intersectionPoint);
//        // Dot product between the normal vector and the ray direction
//        double nv = alignZero(n.dotProduct(v));
//
//        if (nv == 0)
//            // If the dot product is zero, return the emission color (no lighting effect)
//            return color;
//
//        // Material of the geometry
//        Material material = gp.geometry.getMaterial();
//
//        for (LightSource lightSource : scene.lights) {
//            // Direction to the light source
//            Vector l = lightSource.getL(intersectionPoint);
//            // Dot product between the normal vector and the light direction
//            double nl = alignZero(n.dotProduct(l));
//
//            // sign(nl) == sign(nv)
//            if (nl * nv > 0) {
//                // Intensity of the light source at the intersection point
//                Color iL = lightSource.getIntensity(intersectionPoint);
//                // Add the diffuse component of the light
//                color = color.add(iL.scale(calcDiffusive(material, nl)),
//                        // Add the specular component of the light
//                        iL.scale(calcSpecular(material, n, l, nl, v)));
//            }
//        }
//        // Return the final color after considering all light sources
//        return color;
//    }
//
//
//    /**
//     * calcGlobalEffects function is the function that calculate the global effects of the light
//     * taken from instruction's slides
//     *
//     * @param gp
//     * @param ray
//     * @param level
//     * @param k
//     * @return
//     */
//    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
//        Color color = Color.BLACK;
//        Material material = gp.geometry.getMaterial();
//        //the reflection coefficient
//        Double3 kr = material.kR;
//        //in each recursive iteration the impact of the reflection decreases
//        Double3 kkr = k.product(kr);
//        //normal to the geometry
//        Vector n = gp.geometry.getNormal(gp.point);
//        //
//        if (!kkr.lowerThan(MIN_CALC_COLOR_K)) {
//            Ray reflectedRay = constructReflected(gp, ray);
//            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
//            if (reflectedPoint == null)
//                return color.add(scene.background);
//            color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
//        }
//        //
//        Double3 kt = material.kT;
//        //in each recursive iteration the impact of the refraction decreases
//        Double3 kkt = k.product(kt);
//        if (!kkt.lowerThan(MIN_CALC_COLOR_K)) {
//            Ray refractedRay = constructReflected(gp, ray);
//            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
//            if (refractedPoint == null)
//                return color.add(scene.background);
//            color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
//        }
//        return color;
//    }
//
//    /**
//     * Calculates the specular component of the light reflection using the Phong model.
//     *
//     * @param material The material of the geometry.
//     * @param n        The normal vector at the intersection point.
//     * @param l        The direction to the light source.
//     * @param nl       The dot product between the normal vector and the light direction.
//     * @param v        The direction of the ray from the camera.
//     * @return The specular component of the light reflection.
//     */
//    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
//        // Calculate the reflected light direction
//        Vector r = l.subtract(n.scale(alignZero(l.dotProduct(n)) * 2));
//        return material.kS.scale(Math.pow(Math.max(0, alignZero(v.scale((-1)).dotProduct(r))), material.nShininess));
//    }
//
//
//    /**
//     * Calculates the diffuse component of the light reflection using the Lambertian model.
//     *
//     * @param material The material of the geometry.
//     * @param nl       The dot product between the normal vector and the light direction.
//     * @return The diffuse component of the light reflection.
//     */
//    private Double3 calcDiffusive(Material material, double nl) {
//        return material.kD.scale(Math.abs(nl));
//    }
//
//
//    /**
//     * Constructs a reflected ray based on the intersection point and the incoming ray.
//     *
//     * @param geoPoint The intersection point.
//     * @param ray      The incoming ray.
//     * @return The reflected ray.
//     */
//    private Ray constructReflected(GeoPoint geoPoint, Ray ray) {
//        Vector v = ray.getDir();
//        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
//        double nv = alignZero(v.dotProduct(n));
//
//        // Calculate the reflected direction: r = v - 2 * (v * n) * n
//        Vector r = v.subtract(n.scale(2d * nv)).normalize();
//
//        // Construct and return the reflected ray
//        return new Ray(geoPoint.point, r);
//    }
//
//
//    /**
//     * constructReflectedRay function is the function that checks if the object is reflective
//     * and if it is, it calculates the reflected ray
//     */
//
//    /**
//     * find the closest intersection of the ray with the objects in the scene
//     * using the findGeoIntersections function from the ray class
//     *
//     * @param ray the ray we're finding the closest intersection to
//     * @return the closest intersection point
//     */
//    private GeoPoint findClosestIntersection(Ray ray) {
//        if (ray == null) {
//            return null;
//        }
//
//        List<GeoPoint> points = scene.geometries.findGeoIntersections(ray);
//        if (points == null) {
//            return null;
//        }
//        return ray.findClosestGeoPoint(points);
//    }
//
//    /* taken from instruction's slides*/
//    @Override
//    public Color traceRay(Ray ray) {
//        GeoPoint closestGeoPoint = findClosestIntersection(ray);
//
//        // If no intersection is found, return the background color
//        if (closestGeoPoint == null) {
//            return scene.background;
//        }
//
//        // Calculate the color at the closest intersection point
//        return calcColor(closestGeoPoint, ray);
//    }
//
//}

public class RayTracerBasic extends RayTracerBase {
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final Double3 INITIAL_K = Double3.ONE;

    /**
     * Ctor to initialize with a scene
     *
     * @param scene a scene to initialize with
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        var point = findClosestIntersection(ray);
        return point == null ? scene.background : calcColor(point, ray);
    }

    /**
     * Calculates the color of a point on a ray.
     *
     * @param gp  the intersection point
     * @param ray the ray
     * @return the calculated color
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.getAmbientLight().getIntensity());
    }


    /**
     * A recursive overload that calculates the color of a point with emission color
     * and local effects.
     *
     * @param geoPoint the intersection point to calculate the color for
     * @param ray      the ray that intersected with the point
     * @return the color of the point after taking emission and local effects into
     *         account
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        var color = calcLocalEffects(geoPoint, ray, k);
        return level == 1 ? color : color.add(calcGlobalEffects(geoPoint, ray, level, k));
    }

    /**
     * Calculates the local effects (diffuse and specular) on a given point for each
     * light source in the scene.
     *
     * @param geoPoint the intersection point to calculate the local effects for
     * @param ray      the ray that intersected with the point
     * @return the local effects on the point as a Color object
     */
//    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray, Double3 k) {
//        var color = geoPoint.getGeometry().getEmission();
//        Vector v = ray.getDir();
//        Vector n = geoPoint.getGeometry().getNormal(geoPoint.getPoint());
//        double nv = alignZero(n.dotProduct(v));
//
//        // if the ray is orthogonal to the point's normal it does not make any effect:
//        if (isZero(nv))
//            return color;
//
//        Material mat = geoPoint.getGeometry().getMaterial();
//        for (LightSource light : scene.lights) {
//            Vector l = light.getL(geoPoint.getPoint());
//            double nl = n.dotProduct(l);
//
//            // Make sure that the perspective (camera) and the light source, are both in
//            // the same side of the point's tangent plane sign(nl) == sign(nv)
//            if (alignZero(nl * nv) > 0) {
//                Double3 ktr = transparency(geoPoint, light, l, n);
//              //  if (ktr.product(k).greaterThan(MIN_CALC_COLOR_K)) {
//                if (ktr.product(k).greaterThan(MIN_CALC_COLOR_K)) {
//                    Color iL = light.getIntensity(geoPoint.getPoint()).scale(ktr);
//                    color = color.add(iL.scale(calcDiffusive(mat, nl)), iL.scale(calcSpecular(mat, n, l, nl, v)));
//                }
//            }
//        }
//        return color;
//    }

    //TODO : instead on previous calcLocalEffects function
    private Color calcLocalEffects(GeoPoint intersection, Ray ray, Double3 k) {
        // (Kd * |l.dorProduct(n)| ) * Il + (Ks * max(0 ,(-v).dotProduct(r))  nShinines) * Il
        // l = vector from light source to the point
        // n = normal vector to shape at point
        // r = specular vector to vector from light to point
        // v = ray from camera to point


        // v
        Vector v = ray.getDir();
        // n
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return Color.BLACK;
        //  nShininess
        int nShininess = intersection.geometry.getMaterial().nShininess;
        // Kd
        Double3 kD = intersection.geometry.getMaterial().kD;
        // Ks
        Double3 kS = intersection.geometry.getMaterial().kS;
        Color color = Color.BLACK;
        // loop through all light sources in scene

        var lights = scene.lights;
        if (softShadow) {
            for (var lightSource : lights) {
                Color colorBeam = Color.BLACK;
                var vectors = lightSource.getListL(intersection.point);
                for (var l:vectors) {

                    // l.dorProduct(n)
                    double nl = alignZero(n.dotProduct(l));
                    // check that light direction is towards shape and not behind
                    if (nl * nv > 0) { // sign(nl) == sing(nv)

                        Double3 ktr = transparency(intersection, lightSource, l, n);
                        if (ktr.scale(k).greaterThan(MIN_CALC_COLOR_K)) {
                            Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
                            // (Kd * |l.dorProduct(n)|) * Il
                            colorBeam = colorBeam.add(calcDiffusive(kD, nl, lightIntensity),
                                    // (Ks * max(0 ,(-v).dotProduct(r))  nShinines ) * Il
                                    calcSpecular(kS, nl, l, n, v, nShininess, lightIntensity));
                        }
                    }
                }
                color=color.add(colorBeam.reduce(vectors.size()));
            }
        }
        else {
            for (var lightSource : lights) {
                // l
                Vector l = lightSource.getL(intersection.point);
                // l.dorProduct(n)
                double nl = alignZero(n.dotProduct(l));
                // check that light direction is towards shape and not behind
                if (nl * nv > 0) { // sign(nl) == sing(nv)

                    Double3 ktr = transparency(intersection, lightSource, l, n);
                    if (ktr.scale(k).greaterThan(MIN_CALC_COLOR_K)) {
                        Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
                        // (Kd * |l.dorProduct(n)|) * Il
                        color = color.add(calcDiffusive(kD, nl, lightIntensity),
                                // (Ks * max(0 ,(-v).dotProduct(r)) ** nShinines ) * Il
                                calcSpecular(kS, nl, l, n, v, nShininess, lightIntensity));
                    }
                }
            }
        }
        return color;
    }



    /**
     * Calculate the reflection and refracted color on given point and ray
     *
     * @param gp    the point
     * @param ray   the ray
     * @param level recursion level
     * @param k     amount of color of that colors to calculate
     * @return the color
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Vector v = ray.getDir();
        Vector n = gp.getGeometry().getNormal(gp.getPoint());
        Material material = gp.getGeometry().getMaterial();
        return calcGlobalEffect(constructReflectedRay(gp, v, n), level - 1, k, material.kR)
                .add(calcGlobalEffect(constructRefractedRay(gp, v, n), level - 1, k, material.kT));
    }

    /**
     * an overload to calc the reflection and refracted color on given point and
     * ray.
     *
     * @param ray   the ray.
     * @param level recursion level.
     * @param k     amount of color to calculate.
     * @param kx    kr / kt ratio.
     * @return the calculated color.
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 k, Double3 kx) {
        Double3 kkx = k.product(kx);
        if (kkx.lowerThan(MIN_CALC_COLOR_K))
            return Color.BLACK;

        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? scene.background.scale(kx) : calcColor(gp, ray, level - 1, kkx)).scale(kx);
    }

    /**
     * Finds the closest intersection point on a ray
     *
     * @param ray the ray
     * @return the closest point
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        var intersections = scene.geometries.findGeoIntersections(ray);
        return intersections == null ? null : ray.findClosestGeoPoint(intersections);
    }

    private Double3 calcDiffusive(Material mat, double nl) {
        return mat.kD.scale(nl >= 0 ? nl : -nl);
    }

    private Double3 calcSpecular(Material mat, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(l.dotProduct(n) * 2));
        double minusVR = alignZero(-v.dotProduct(r));
        return minusVR <= 0 ? Double3.ZERO : mat.kS.scale(Math.pow(minusVR, mat.nShininess));
    }

    /**
     * Checks if a given geopoint is not shaded by a light source.
     *
     * @param gp    a geopoint to check for shading.
     * @param light a light source in the scene.
     * @param l     The direction vector from the light source to the point.
     * @param n     The normal vector of the point.
     * @return true if the point is unshaded.
     */
    @SuppressWarnings("unused")
    private boolean unshaded(GeoPoint gp, LightSource light, Vector l, Vector n) {
        var lightDirection = l.scale(-1); // vector from point to light source
        Ray lightRay = new Ray(gp.getPoint(), lightDirection, n);
        return scene.geometries.findGeoIntersections(lightRay, light.getDistance(gp.getPoint())) == null;
    }

    /**
     * construct the refracted ray of a ray and a point
     *
     * @param gp the point
     * @param v  the ray
     * @param n  the normal
     * @return the refracted ray
     */
    public static Ray constructRefractedRay(GeoPoint gp, Vector v, Vector n) {
        return new Ray(gp.getPoint(), v, n);
    }

    /**
     * construct the reflected ray using a ray and a point
     *
     * @param gp the point
     * @param v  the ray's direction
     * @param n  the normal
     * @return the reflected ray
     */
    public static Ray constructReflectedRay(GeoPoint gp, Vector v, Vector n) {
        return new Ray(gp.getPoint(), v.subtract(n.scale(2 * alignZero(n.dotProduct(v)))), n);
    }

    /**
     * Calculating the amount of shade in given point
     *
     * @param geoPoint the GeoPoint
     * @param light    the light source
     * @param l        the vector from the light
     * @param n        the normal
     * @return amount of light that come to that point
     */

    private Double3 transparency(GeoPoint geoPoint, LightSource light, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geoPoint.getPoint(), lightDirection, n);
        var intersections = scene.geometries.findGeoIntersections(lightRay, light.getDistance(geoPoint.getPoint()));
        if (intersections == null)
            return Double3.ONE;

        Double3 ktr = Double3.ONE;
        for (GeoPoint gp : intersections) {
            ktr = ktr.product(gp.getGeometry().getMaterial().kT);
            if (ktr.lowerThan(MIN_CALC_COLOR_K))
                return Double3.ZERO;
        }
        return ktr;
    }
}