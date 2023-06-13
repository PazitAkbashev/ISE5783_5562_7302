package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Pazit and Leah
 */
public abstract class Intersectable {

    /**
     * Find all Intersections points from the ray.
     *
     * @param ray MUST be not null, The ray tested at the intersection of the object
     * @return List of points that intersection with the object
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream()
                .map(gp -> gp.point)
                .toList();
    }



    /**
     * Finds the intersection points between a given ray and the geometry objects within the scene,
     * up to an infinite maximum distance.
     * @param ray The ray to find intersections with.
     * @return A list of GeoPoint objects representing the intersection points, or null if no intersections are found.
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        // Call the overloaded findGeoIntersections() function with a default maximum distance of positive infinity
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }


    /**
     * Finds the intersection points between a given ray and the geometry objects within the scene,
     * up to a specified maximum distance.
     * @param ray         The ray to find intersections with.
     * @param maxDistance The maximum distance for valid intersections.
     * @return A list of GeoPoint objects representing the intersection points, or null if no intersections are found.
     */
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        // Call the helper function findGeoIntersectionsHelper() with the given ray and maximum distance
        return this.findGeoIntersectionsHelper(ray, maxDistance);
    }


    abstract protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);

    /**
     * inner class
     * Represents a point of intersection between a ray and a geometry object.
     */
    public static class GeoPoint {
        // The geometry object of the intersection
        public final Geometry geometry;
        // The point of intersection
        public final Point point;

        /**
         * Constructs a GeoPoint object with the given geometry and point.
         * @param geometry The geometry object of the intersection.
         * @param point    The point of intersection.
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.point = point;
            this.geometry = geometry;
        }

        public Geometry getGeometry() {
            return geometry;
        }

        public Point getPoint() {
            return point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
        }

        @Override
        public int hashCode() {
            return Objects.hash(geometry, point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }

}
