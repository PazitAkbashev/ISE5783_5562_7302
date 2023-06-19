package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

///**
// * Class describe a plane,
// * contains some point on the surface and normal vector from the point.
// *
// * @author pazit and Leah
// */
//public class Plane extends Geometry {
//
//    private final Point q0;
//    private final Vector normal;
//
//    /**
//     * constructor create plane from one point and normal vector from the point.
//     *
//     * @param p0            -some point on the surface
//     * @param vector-Normal to the plane Normalize the vector in a state that is not normalized
//     */
//    public Plane(Point p0, Vector vector) {
//        this.q0 = p0;
//        if (!(isZero(vector.length() - 1d))) {
//            this.normal = vector.normalize();
//        } else {
//            this.normal = vector;
//        }
//    }
//
//    /**
//     * Constructor create plane from 3 points on the surface.
//     * the points are ordered from right to left
//     * we calculate the normal on the constructor to avoid repeated request of the normal
//     *
//     * @param p1 first point
//     * @param p2 second point
//     * @param p3 third point
//     * @throws IllegalArgumentException if UxV = (0,0,0) => all 3 point on the same line
//     */
//    public Plane(Point p1, Point p2, Point p3) {
//        q0 = p1;
//        if (p1.equals(p2) || p1.equals(p3) || p2.equals(p3)) {
//            throw new IllegalArgumentException("Two of the points are identical");
//        }
//        //vector from p1 towards p2
//        Vector v1 = p2.subtract(p1);
//        //vector from p1 towards p3
//        Vector v2 = p3.subtract(p1);
//
//        if (v1.normalize().equals(v2.normalize())) {
//            throw new IllegalArgumentException("There is a linear dependence between the vectors");
//        }
//
//        Vector n = v1.crossProduct(v2);
//
//        normal = n.normalize();
//    }
//    //region getters
//
//    /**
//     * getter of point p0
//     *
//     * @return p0 - A point in the plane
//     */
//    public Point getP0() {
//        return q0;
//    }
//
//    /**
//     * getter vector normal
//     *
//     * @return normal-Normal to the plane
//     */
//    public Vector getNormal() {
//        return normal;
//    }
//
//    @Override
//    public Vector getNormal(Point point) {
//        return getNormal();
//    }
//
//    @Override
//    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
//        Point P0 = ray.getP0();
//        Vector v = ray.getDir();
//        Vector n = normal;
//
//        //denominator
//        double nv = n.dotProduct(v);
//
//        // if v is lying on the plane
//        // there is infinite number of intersections points
//        // in this case we return null
//
//        if (isZero(nv)) {
//            return null;
//        }
//
//        //ray cannot start from the plane
//        if (q0.equals(P0)) {
//            return null;
//        }
//
//        Vector P0_Q0 = q0.subtract(P0);
//
//        //numerator
//        double nP0Q0 = n.dotProduct(P0_Q0);
//
//        // ray starting from to the plane
//        if (isZero(nP0Q0)) {
//            return null;
//        }
//
//        double t = alignZero(nP0Q0 / nv);
//
//        if (alignZero(t - maxDistance) > 0) {
//            return null;
//        }
//
//        // Point point = ray.getPoint(t);
//
//        GeoPoint point = new GeoPoint(this, ray.getPoint(t));
//        return List.of(point);
//    }
//}


public class Plane extends Geometry {
    private final Point q0;
    private final Vector normal;

    /**
     * A ctor to initialize a Plane with 3 points
     *
     * @param q1 First Point
     * @param q2 Second Point
     * @param q3 Third Point
     */
    public Plane(Point q1, Point q2, Point q3) {
        q0 = q1;
        normal = (q3.subtract(q1)).crossProduct(q3.subtract(q2)).normalize();
    }

    /**
     * A ctor to initialize a Plane with a Point and a normal Vector
     *
     * @param q0     A point on the Plane
     * @param normal A normal Vector to the Plane
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    /** @return A normal to the Plane */
    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point p) {
        return normal;
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDis) {
        Point p0 = ray.getP0();
        Vector n = normal;

        // Check if the ray and the triangle are parallel
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
        return alignZero(t) <= 0 || alignZero(t - maxDis) > 0 ? null : List.of(new GeoPoint(this, ray.getPoint(t)));
    }

}