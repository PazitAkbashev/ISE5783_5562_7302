package geometries;
import primitives.Point;
import primitives.Ray;
import java.util.List;

/**
 * ***********************interface ti implement in stage 3 ********************* change the comment
 */
public interface Intersectable {
    List<Point> findIntersections(Ray ray);
}
