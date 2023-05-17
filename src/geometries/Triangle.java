package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Class describe triangle, polygon with 3 points.
 *
 * @author Pazit and Leah
 */
public class Triangle extends Polygon {

    /**
     * Constructor create triangle from 3 points.
     *
     * @param p1 first point
     * @param p2 second point
     * @param p3 third point
     * @throws IllegalArgumentException if the points can't create triangle (same points etc.)
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {

        /** If ray doesn't intersect the plan consist in triangle return null */
        List<Point> result = plane.findIntersections(ray);
        if (result == null) {
            return null;
        }

        Vector v = ray.getDir();
        Point p0 = ray.getP0();

        boolean isThereIntersections;

        /** The triangle is defined by 3 points, p1, p2, p3.*/
        Point point1 = vertices.get(0);
        Point point2 = vertices.get(1);
        Point point3 = vertices.get(2);

        /** The triangle is defined by 3 vectors, v1, v2, v3.*/
        Vector vector1 = point1.subtract(p0);
        Vector vector2 = point2.subtract(p0);
        Vector vector3 = point3.subtract(p0);

        /** The triangle is defined by 3 normals, normal1, normal2, normal3.*/
        Vector normal1 = vector1.crossProduct(vector2);
        Vector normal2 = vector2.crossProduct(vector3);
        Vector normal3 = vector3.crossProduct(vector1);

        /** The triangle is defined by 3 signs, s1, s2, s3.*/
        double s1 = alignZero(v.dotProduct(normal1));
        double s2 = alignZero(v.dotProduct(normal2));
        double s3 = alignZero(v.dotProduct(normal3));

        /** If all signs are positive or negative, there is no intersections.*/
        isThereIntersections = (s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0);
        if (!isThereIntersections)
            return null;

        return result;
    }
}



