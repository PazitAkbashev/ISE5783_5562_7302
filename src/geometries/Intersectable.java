package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;
import java.util.Objects;

///**
// * @author Pazit and Leah
// */
//public abstract class Intersectable {
//
//    /**
//     * Find all Intersections points from the ray.
//     *
//     * @param ray MUST be not null, The ray tested at the intersection of the object
//     * @return List of points that intersection with the object
//     */
//    public final List<Point> findIntersections(Ray ray) {
//        var geoList = findGeoIntersections(ray);
//        return geoList == null ? null
//                : geoList.stream()
//                .map(gp -> gp.point)
//                .toList();
//    }
//
//
//    /**
//     * Finds the intersection points between a given ray and the geometry objects within the scene,
//     * up to an infinite maximum distance.
//     *
//     * @param ray The ray to find intersections with.
//     * @return A list of GeoPoint objects representing the intersection points, or null if no intersections are found.
//     */
//    public final List<GeoPoint> findGeoIntersections(Ray ray) {
//        // Call the overloaded findGeoIntersections() function with a default maximum distance of positive infinity
//        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
//    }
//
//
//    /**
//     * Finds the intersection points between a given ray and the geometry objects within the scene,
//     * up to a specified maximum distance.
//     *
//     * @param ray         The ray to find intersections with.
//     * @param maxDistance The maximum distance for valid intersections.
//     * @return A list of GeoPoint objects representing the intersection points, or null if no intersections are found.
//     */
//    public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
//        // Call the helper function findGeoIntersectionsHelper() with the given ray and maximum distance
//        return findGeoIntersectionsHelper(ray, maxDistance);
//    }
//
//
//    abstract protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);
//
//    /**
//     * inner class
//     * Represents a point of intersection between a ray and a geometry object.
//     */
//    public static class GeoPoint {
//        // The geometry object of the intersection
//        public final Geometry geometry;
//        // The point of intersection
//        public final Point point;
//
//        /**
//         * Constructs a GeoPoint object with the given geometry and point.
//         *
//         * @param geometry The geometry object of the intersection.
//         * @param point    The point of intersection.
//         */
//        public GeoPoint(Geometry geometry, Point point) {
//            this.point = point;
//            this.geometry = geometry;
//        }
//
//        public Geometry getGeometry() {
//            return geometry;
//        }
//
//        public Point getPoint() {
//            return point;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            GeoPoint geoPoint = (GeoPoint) o;
//            return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(geometry, point);
//        }
//
//        @Override
//        public String toString() {
//            return "GeoPoint{" +
//                    "geometry=" + geometry +
//                    ", point=" + point +
//                    '}';
//        }
//    }
//
//}


public abstract class Intersectable {
    /** A helper PDS class to keep track of the geometry owner of the point */
    public static class GeoPoint {
        /** The geometry */
        public Geometry geometry;
        /** A point on the geometry (meant to be an intersection point with a ray) */
        public Point point;

        /**
         * Ctor to initialize with a geometry and a point
         *
         * @param geometry The geometry
         * @param point    A point on the geometry
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj instanceof GeoPoint other)
                return other.point == point && other.geometry == geometry;
            return false;
        }

        @Override
        public String toString() {
            return "GeoPoint{geometry: " + geometry + " | point: " + point + "}";
        }
    }

    /**
     * finds all intersection points between the intersectable geometry and a Ray
     *
     * @param ray The ray
     * @return a list of intersection Points
     */
    public final List<Point> findIntersections(Ray ray) {
        var tmp = findGeoIntersections(ray);
        // first checking nullity, before calling stream()...
        return tmp == null ? null : tmp.stream().map(gp -> gp.point).toList();
    }

    /**
     * finds all geo intersection points between the intersectable geometry and a
     * Ray
     *
     * @param ray    The ray
     * @param maxDis maximum distance to calculate
     * @return a list of geo intersection Points
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDis);

    /**
     * finds all geo intersection points between the intersectable geometry and a
     * Ray
     *
     * @param ray The ray
     * @return a list of geo-intersection points
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray, Double.POSITIVE_INFINITY);
    }

    /**
     * an overload to that receives max distance
     *
     * @param ray    The ray
     * @param maxDis Maximum distance
     * @return list of geo-intersection points
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDis) {
        return findGeoIntersectionsHelper(ray, maxDis);
    }

}
