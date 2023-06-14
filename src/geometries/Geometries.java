package geometries;

import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author pazit and Lea
 */
public class Geometries extends Intersectable {
    private List<Intersectable> intersectables = new LinkedList<>();

    /**
     * Default constructor for Geometries
     */
    public Geometries() {
        // use LinkedList because the use of this list is only for iterate
        // from the start of the list to the end, and adding new items to her
        intersectables = new LinkedList();
    }

    /**
     * Constructor for Geometries
     * @param intersectables one or more interfaces to add to the geometries list
     */
    public Geometries(Intersectable... intersectables) {
        add(intersectables);
    }

    /**
     * Add interfaces to the list of the geometries
     * @param intersectables one or more interfaces to add to the geometries list
     */
    public void add(Intersectable... intersectables) {
        this.intersectables.addAll(List.of(intersectables));
    }
//    public void add(Intersectable... intersectables) {
//        Collections.addAll(this.intersectables, intersectables);
//    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDis) {
        List<GeoPoint> result = null;
        for (Intersectable geo : intersectables) {
            var tmp = geo.findGeoIntersections(ray, maxDis);
            if (tmp != null) {
                if (result == null)
                    result = new LinkedList<>();
                result.addAll(tmp);
            }
        }
        return result;
    }
//    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
//        List<GeoPoint> result = null;
//        // Iterate through each intersect item
//        for (var item : intersectables)
//
//        {
//            // Find intersection points between the item and the ray
//            List<GeoPoint> itemPoints = item.findGeoIntersections(ray, maxDistance);
//            // If there are intersection points
//            if (itemPoints != null) {
//                // Initialize the result list if it's null
//                if (result == null)
//                {
//                    result = new LinkedList<>();
//                }
//                // Add the item's intersection points to the result list
//                result.addAll(itemPoints);
//            }
//        }
//        return result;
//    }
}
