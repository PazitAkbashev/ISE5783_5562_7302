package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;
import java.util.Objects;

/**
 * Intersectable is an interface for all geometries that are able to intersect
 *
 * @author Pazit and Leah - 26.06.23
 */
public abstract class Intersectable { //++

    /**
     * Finds all Intersections points from the ray with the geometry objects within the scene,
     * up to an infinite maximum distance.
     *
     * @param ray MUST be not null, The ray tested at the intersection of the object
     * @return List of points that has intersection with the object
     */
    public final List<Point> findIntersections(Ray ray) { //++
        // findGeoIntersections() function finds intersections between the ray and the geometry
        // returns a list of GeoPoint objects
        List<GeoPoint> geoList = findGeoIntersections(ray);

        //if the list is null, return null
        return geoList == null ? null
                // else, return a list of points from the GeoPoint objects
                : geoList.stream()
                .map(gp -> gp.point)
                .toList();
    }

    /**
     * Find all Intersections points between the geometry and the ray.
     * Each geometry has its own implementation.
     *
     * @param ray - tht ray to find intersections with
     * @param maxDistance - the maximum distance to find intersections
     * @return List of points that intersection with the object
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance); //++

    /**
     * Finds the intersection points between a given ray and the geometry objects within the scene,
     * up to an infinite maximum distance.
     *
     * @param ray The ray to find intersections with.
     * @return A list of GeoPoint objects representing the intersection points, or null if no intersections are found.
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray) { //++
        // Call the overloaded findGeoIntersectionsHelper()
        // function with a default maximum distance of positive infinity
        return findGeoIntersectionsHelper(ray, Double.POSITIVE_INFINITY);
    }

    /**
     * Finds the intersection points between a given ray and the geometry objects within the scene,
     * up to a specified maximum distance.
     *
     * @param ray - The ray to find intersections with.
     * @param maxDistance - The maximum distance for valid intersections.
     * @return A list of GeoPoint objects representing the intersection points,
     * or null if no intersections are found.
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) { //++
        // Call the helper function findGeoIntersectionsHelper() with the given ray and maximum distance
        return findGeoIntersectionsHelper(ray, maxDistance);
    }

    /**
     * inner class
     * Represents a point of intersection between a ray and a geometry object.
     */
    public static class GeoPoint {
        // The geometry object of the intersection - public not final!!
        public  Geometry geometry; //++

        // The point of intersection
        private final Point point; //++

        /**
         * Constructs a GeoPoint object with the given geometry and point.
         *
         * @param geometry - The geometry object of the intersection.
         * @param point - The point of intersection.
         */
        public GeoPoint(Geometry geometry, Point point) { //++
            //initialize The geometry object of the intersection.
            this.geometry = geometry;

            //initialize The point of intersection.
            this.point = point;
        }

        /**
         * Returns the geometry object of the intersection.
         * geometry defined as private final
         *
         * @return The geometry object of the intersection.
         */
        public Geometry getGeometry() {  //++
            //The geometry object of the intersection.
            return geometry;
        }

        /**
         * Returns the point of intersection.
         * point defined as private final
         *
         * @return The point of intersection.
         */
        public Point getPoint() { //++
            //The point of intersection.
            return point;
        }

        @Override
        public boolean equals(Object obj) { //++
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            GeoPoint geoPoint = (GeoPoint) obj;
            return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
        }

        @Override
        public String toString() { //++
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }
}