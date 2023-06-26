package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Class describe a plane,
 * contains some point on the surface and normal vector from the point.
 *
 * @author Pazit and Leah - 26.06.23
 */
public class Plane extends Geometry {

    //point on the plane
    private final Point q0;

    //normal to the plane
    private final Vector normal;

    /**
     * constructor to initialize a Plane with 3 points
     *
     * @param p1 First Point
     * @param p2 Second Point
     * @param p3 Third Point
     */
    public Plane(Point p1, Point p2, Point p3) {
       //initializing q0
        this.q0 = p1;

        //vector from p2 towards p3
        Vector v1 = p3.subtract(p2);

        //vector from p1 towards p3
        Vector v2 = p3.subtract(p1);

        //initializing normal
        this.normal = v2.crossProduct(v1).normalize();
    }

    /**
     * constructor initialize plane from one point and normal vector from the point.
     *
     * @param p0 - A point on the Plane
     * @param normal - A normal Vector to the Plane
     */
    public Plane(Point p0, Vector normal) {
        //initializing q0
        this.q0 = p0;

        //initializing normal
        this.normal = normal.normalize();
    }

    /**
     * getter of vector normal
     *
     * @return the Normal to the Plane
     */
    public Vector getNormal() { //used in Polygon
        //returning the normal to the plane
        return normal;
    }

    @Override
    public Vector getNormal(Point p) {
        //returning the normal to the plane
        return normal;
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        Point p0 = ray.getP0();
        Vector n = normal;

        // Check if the ray and the triangle are parallel ( => ||)
        double nd = alignZero(n.dotProduct(ray.getDir()));
        if (isZero(nd))
            return null;

        // ray starts in the plane
        if (q0.equals(p0))
            return null;

        // Compute the parameter t
        Vector p0q0 = q0.subtract(p0);
        double t = n.dotProduct(p0q0) / nd;

        // Check if the intersection point is behind the ray origin, or if the ray
        // starts in the plane

        List<GeoPoint> result = alignZero(t) <= 0 || alignZero(t - maxDistance) > 0
                ? null
                : List.of(new GeoPoint(this, ray.getPoint(t)));

        return result;

    }

}