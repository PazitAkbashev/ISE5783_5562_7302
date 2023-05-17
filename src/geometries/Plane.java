package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Class describe a plane,
 * contains some point on the surface and normal vector from the point.
 *
 * @author pazit and Leah
 */
public class Plane implements Geometry {

    public Point q0;
    public Vector normal;

    /**
     * constructor create plane from one point and normal vector from the point.
     *
     * @param p0            -some point on the surface
     * @param vector-Normal to the plane Normalize the vector in a state that is not normalized
     */
    public Plane(Point p0, Vector vector) {
        this.q0 = p0;
        if (!(isZero(vector.length() - 1d))) {
            this.normal = vector.normalize();
        } else {
            this.normal = vector;
        }
    }

    /**
     * Constructor create plane from 3 points on the surface.
     * the points are ordered from right to left
     * we calculate the normal on the constructor to avoid repeated request of the normal
     *
     * @param p1 first point
     * @param p2 second point
     * @param p3 third point
     * @throws IllegalArgumentException if UxV = (0,0,0) => all 3 point on the same line
     */
    public Plane(Point p1, Point p2, Point p3) {
        q0 = p1;
        if (p1.equals(p2) || p1.equals(p3) || p2.equals(p3)) {
            throw new IllegalArgumentException("Two of the points are identical");
        }

        Vector v1 = p2.subtract(p1);  //vector from p1 towards p2
        Vector v2 = p3.subtract(p1);  //vector from p1 towards p3

        if (v1.normalize().equals(v2.normalize())) {
            throw new IllegalArgumentException("There is a linear dependence between the vectors");
        }

        Vector n = v1.crossProduct(v2);

        normal = n.normalize();
    }
    //region getters

    /**
     * getter of point p0
     *
     * @return p0 - A point in the plane
     */
    public Point getP0() {
        return q0;
    }

    /**
     * getter vector normal
     *
     * @return normal-Normal to the plane
     */
    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point point) {
        return getNormal();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        Point P0 = ray.getP0();
        Vector v = ray.getDir();
        Vector n = normal;

        //denominator
        double nv = n.dotProduct(v);

        // if v is lying on the plane
        // there is infinite number of intersections points
        // in this case we return null

        if (isZero(nv)) {
            return null;
        }

        //ray cannot start from the plane
        if (q0.equals(P0)) {
            return null;
        }

        Vector P0_Q0 = q0.subtract(P0);

        //numerator
        double nP0Q0 = n.dotProduct(P0_Q0);

        // ray parallel to the plane
        if (isZero(nP0Q0)) {
            return null;
        }

        double t = alignZero(nP0Q0 / nv);

        if (t < 0) {
            return null;
        }

        Point point = ray.getPoint(t);

        return List.of(point);
    }
}
