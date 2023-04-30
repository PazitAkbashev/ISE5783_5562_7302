package geometries;
import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * this class represent a Triangle by 3 points
 * @author Pazit and Leah
 */
public class Triangle extends Polygon {
    /**
     * constructor of triangle
     *
     * @param p1 Vertex 1
     * @param p2 Vertex 2
     * @param p3 Vertex 3
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }
    @Override
    public List<Point> findIntersections(Ray ray) {
        /* If ray doesn't intersect the plan consist in triangle return null */
        if (plane.findIntersections(ray) == null) {
            return null;
        }
        List<Point> result = null;
        Vector vector, vector1, vector2, vector3;
        Vector normal1, normal2, normal3;
        Point point1, point2, point3;
        boolean isThereIntersections;

        vector = ray.getDir();

        point1 = vertices.get(0);
        point2 = vertices.get(1);
        point3 = vertices.get(2);

        /* Otherwise, check if ray intersect the triangle */
        vector1 = point1.subtract(ray.getP0());
        vector2 = point2.subtract(ray.getP0());
        vector3 = point3.subtract(ray.getP0());

        normal1 = vector1.crossProduct(vector2);
        normal2 = vector2.crossProduct(vector3);
        normal3 = vector3.crossProduct(vector1);

        isThereIntersections = vector.dotProduct(normal1) > 0 && vector.dotProduct(normal2) > 0 &&
                vector.dotProduct(normal3) > 0;

        isThereIntersections = isThereIntersections ||
                vector.dotProduct(normal1) < 0 && vector.dotProduct(normal2) < 0 &&
                        vector.dotProduct(normal3) < 0;

        if (isThereIntersections) {
            result = plane.findIntersections(ray);
        }
        return result;
    }
}



