package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * RayTracerBasic class is the basic class for ray tracing in the scene
 * It is responsible for the calculation of the color of the pixel
 * It is an abstract class that extended by RayTracerBasic class
 *
 * @author Pazit and Lea
 */
public class RayTracerBasic extends RayTracerBase {

    //max level of recursion
    private static final int MAX_CALC_COLOR_LEVEL = 10;

    //min level of k
    private static final double MIN_CALC_COLOR_K = 0.001;

    //initial k
    private static final Double3 INITIAL_K = Double3.ONE;


    /**
     * constructor for RayTracerBasic class
     *
     * @param scene the scene we want to
     */
    public RayTracerBasic(Scene scene) {
        //initialize the scene we want to trace
        super(scene);
    }

    /**
     * method to calculate the color of the closest point to the camera
     * by recursive calls to calcColor
     *
     * @param gp the closest point to the camera
     * @param ray
     * @return the color of the closest point to the camera which on the ray
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene
                        .getAmbientLight()
                        .getIntensity());
    }

    /**
     * method to calculate the color of the closest point to the camera
     *
     * @param geoPoint the closest point to the camera
     * @param ray
     * @param level - the level of recursion
     * @param k - the level of k
     * @return
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        var color = calcLocalEffects(geoPoint, ray, k);
        return level == 1 ? color : color.add(
                calcGlobalEffects(geoPoint, ray, level, k));
    }

    /**
     * method to calculate the local effects of the closest point to the camera
     *
     * @param geoPoint the closest point to the camera
     * @param ray
     * @param k - the level of k
     * @return the color of the closest point to the camera which on the ray
     */
    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray, Double3 k) {
        var color = geoPoint.getGeometry().getEmission();
        Vector v = ray.getDir();
        Vector n = geoPoint.getGeometry().getNormal(geoPoint.getPoint());
        double nv = alignZero(n.dotProduct(v));

        //====================================
        int nShininess = geoPoint.getGeometry().getMaterial().nShininess;
        Double3 kD = geoPoint.getGeometry().getMaterial().kD;
        Double3 kS = geoPoint.getGeometry().getMaterial().kS;

        //====================================
        // if the ray is orthogonal to the point's normal it does not make any effect:
        if (isZero(nv))
            return color;

        Material mat = geoPoint.getGeometry().getMaterial();
        for (LightSource light : scene.lights) {
            Vector l = light.getL(geoPoint.getPoint());
            double nl = n.dotProduct(l);

            // Make sure that the perspective (camera) and the light source, are both in
            // the same side of the point's tangent plane sign(nl) == sign(nv)
            if (alignZero(nl * nv) > 0) {
                Double3 ktr = transparency(geoPoint, light, l, n);
                //================================================
                //if soft shadow is not activated, get the regular transparency
                if (!isSoftShadow) {
                    ktr = transparency(geoPoint, light, l, n);
                    //otherwise get the transparency level according to soft shadow
                } else {
                    ktr = transparencySS(geoPoint, light, n);
                }
                if (!(ktr.product(k)).lowerThan(MIN_CALC_COLOR_K)) {
                    Color lightIntensity = light.getIntensity(geoPoint.getPoint()).scale(ktr);
                    color = color.add(calcDiffusive(kD, l, n, lightIntensity),
                            calcSpecular(kS, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        return color;

        //=================================================
    }

    /**
     * Calculates the reflection and the refraction
     * at a given intersection point.
     *
     * @param gp - the intersection point
     * @param ray - the ray that caused the intersection
     * @param level - the number of the recursive calls
     *              to calculate the next reflections and
     *              refractions
     * @param k - the effect's strength by the reflection and refraction
     * @return the color on the intersection point
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        //the direction of the ray
        Vector v = ray.getDir();

        //the normal of the point
        Vector n = gp.getGeometry().getNormal(gp.getPoint());

        //the material of the point
        Material material = gp.getGeometry().getMaterial();

        Color color = calcGlobalEffect(constructReflectedRay(gp, v, n)
                , level - 1, k, material.kR)
                .add(calcGlobalEffect(constructRefractedRay(gp, v, n)
                        , level - 1, k, material.kT));
        return color;
    }

    /**
     * Calculates the reflection and the refraction
     *
     * @param ray - the ray that caused the intersection
     * @param level - the number of the recursive calls
     *              to calculate the next reflections and
     *              refractions
     * @param k - the effect's strength by the reflection and refraction
     * @param kx - the effect's strength by the reflection and refraction
     * @return the color on the intersection point
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 k, Double3 kx) {
        Double3 kkx = k.product(kx);
        if (kkx.lowerThan(MIN_CALC_COLOR_K))
            return Color.BLACK;

        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? scene.background.scale(kx)
                : calcColor(gp, ray, level - 1, kkx)).scale(kx);
    }

    /**
     * find the closest intersection of the ray with the objects in the scene
     * using the findGeoIntersections function from the ray class
     *
     * @param ray the ray we're finding the closest intersection to
     * @return the closest intersection point
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null)
            return null;
        return ray.findClosestGeoPoint(intersections);
    }

    private Double3 calcSpecular(Material mat, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(l.dotProduct(n) * 2));
        double minusVR = alignZero(-v.dotProduct(r));
        return minusVR <= 0 ? Double3.ZERO : mat.kS.scale(Math.pow(minusVR, mat.nShininess));
    }

    /**
     * transparency function is the function that checks if the object is transparent
     *
     * @param gp    the point
     * @param light the light source
     * @param l     the vector from the point to the light source
     * @param n     the normal
     * @return the transparency
     */
    @SuppressWarnings("unused")
    private boolean unshaded(GeoPoint gp, LightSource light, Vector l, Vector n) {
        // vector from point to light source
        var lightDirection = l.scale(-1);
        Ray lightRay = new Ray(gp.getPoint(), lightDirection, n);
        boolean result = scene.geometries.findGeoIntersections(lightRay, light.getDistance(gp.getPoint())) == null;
        return result;
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
    public static Ray constructReflectedRay(GeoPoint gp, Vector v, Vector n) { //++
        double vn = alignZero(n.dotProduct(v));
        //the reflected direction: r = v - 2 * (v * n) * n
        Ray ray = new Ray(gp.getPoint(), v.subtract(n.scale(2d * vn)), n);
        return ray;
    }


    private Double3 transparency(GeoPoint geoPoint, LightSource light, Vector l, Vector n) { //++
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


    /* taken from instruction's slides*/
    @Override
    public Color traceRay(Ray ray) { //++
        GeoPoint closestGeoPoint = findClosestIntersection(ray);

        // If no intersection is found, return the background color
        if (closestGeoPoint == null) {
            return scene.background;
        }

        // Calculate the color at the closest intersection point
        return calcColor(closestGeoPoint, ray);
    }


    private boolean isSoftShadow = false;

    private int numOfSSRays = 10;


    private double radiusBeamSS = 10;


    public RayTracerBasic useSoftShadow(boolean flag) {
        this.isSoftShadow = flag;
        return this;
    }

    public RayTracerBasic setNumOfSSRays(int num) {
        if (num <= 0) {
            throw new IllegalArgumentException(
                    "Number of rays must be greater than 0");
        }

        this.numOfSSRays = num;
        return this;
    }

    public RayTracerBasic setRadiusBeamSS(double r) {
        if (r <= 0) {
            throw new IllegalArgumentException(
                    "Radius of beam must be greater than 0");
        }
        this.radiusBeamSS = r;
        return this;
    }

    /**
     * Returns transparency level with soft shadow effect
     * Constructs random rays around the light and gets the average of all the levels
     *
     * @param gp the point to check
     * @param ls the current light source
     * @param n  normal to the point
     * @return average ktr
     */
    private Double3 transparencySS(GeoPoint gp, LightSource ls, Vector n) {
        Double3 ktr = Double3.ZERO;
        List<Vector> vecs = ls.getLCircle(gp.getPoint(), radiusBeamSS, numOfSSRays);

        for (Vector v : vecs) { //for each vector, add the transparency level there
            ktr = ktr.add(transparency(gp, ls, v, n));
        }

        ktr = ktr.reduce(vecs.size()); //get the average of all the transparency levels of all the vectors

        return ktr;
    }

    /** Calculate the diffuse light effect on the point
     * @param kd diffuse attenuation factor
     * @param l the direction of the light
     * @param n normal from the point
     * @param lightIntensity the intensity of the light source at this point
     * @return the color
     */
    private Color calcDiffusive(Double3 kd, Vector l, Vector n, Color lightIntensity) {
//        double ln = alignZero(abs(l.dotProduct(n))); //ln=|l*n|
//        return lightIntensity.scale(kd.scale(ln)); //Kd * |l * n| * Il
        double nl = n.dotProduct(l);
        double abs_nl = Math.abs(nl);
        Double3 amount = kd.scale(abs_nl);
        return lightIntensity.scale(amount);
    }

    private Double3 calcDiffusive(Material mat, double nl) {
        return mat.kD.scale(nl >= 0 ? nl : -nl);
    }

    /** Calculate the specular light at this point
     * @param ks specular attenuation factor
     * @param l the direction of the light
     * @param n normal from the point
     * @param v direction of the viewer
     * @param nShininess shininess factor of the material at the point
     * @param lightIntensity the intensity of the light source at the point
     * @return the color of the point
     */
    private Color calcSpecular(Double3 ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {

        double nl = n.dotProduct(l);
        Vector r = l.add(n.scale(-2 * nl)); // nl must not be zero!
        double minusVR = -alignZero(r.dotProduct(v));
        if (minusVR <= 0)
            return Color.BLACK; // view from direction opposite to r vector
        Double3 amount = ks.scale(Math.pow(minusVR, nShininess));
        return lightIntensity.scale(amount);
    }
}




