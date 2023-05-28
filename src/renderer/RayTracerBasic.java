package renderer;

import geometries.Intersectable.GeoPoint;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.*;
import scene.Scene;
import primitives.Double3;

import java.util.List;

import static java.awt.Color.yellow;
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
        return scene.ambientLight.getIntensity()
                .add(calcLocalEffects(gp, ray));
    }

    /**
     * calcLocalEffects function is the function that calculate the local effects of the light
     *
     * @param gp the intersection point
     * @param ray the ray from the camera
     * @return the color of the local effects
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));

        if (nv == 0)
            return color;

        Material material = gp.geometry.getMaterial();

        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));

            if (nl * nv > 0) { // sign(nl) == sign(nv)
                Color iL = lightSource.getIntensity(gp.point);
                color = color.add(iL.scale(calcDiffusive(material, nl)),
                        iL.scale(calcSpecular(material, n, l, nl, v)));
            }
        }

        return color;
    }

private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
    Vector r = l.subtract(n.scale(alignZero(l.dotProduct(n)) * 2));
    return material.kS.scale(Math.pow(Math.max(0, alignZero(v.scale((-1)).dotProduct(r))), material.nShininess));
}

private Double3 calcDiffusive(Material material, double nl) {
    return material.kD.scale(Math.abs(nl));
}

    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> list = scene.geometries.findGeoIntersectionsHelper(ray);
        return list == null ? scene.background : calcColor(ray.findClosestGeoPoint(list), ray);
    }
}
